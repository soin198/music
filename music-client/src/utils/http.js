import axios from "axios";
import router from "@/router/index";
import {paramsSerializer} from "@/utils/core";
import {clearProfile} from "@/utils/session";
import DataCache from "@/utils/DataCacheUtils";
import NoAuthPage from "@/config/NoAuthPage";
import jka from "./jka";
import {_trackEvent} from '@/utils/umTrack'

const baseURL = '/api';

const instance = axios.create({
    baseURL,
    timeout: 180000,
    paramsSerializer,
    headers: {
        Authorization: `${DataCache.getToken()}`,
    },
});
instance.interceptors.request.use(function (config) {
    config.headers["Authorization"] = `${DataCache.getToken()}`;
    return config;
}, function (error) {

})

instance.interceptors.response.use(response => response.data, async error => {
    const response = error.response;
    if (response) {
        // 需要授权登录
        if (response.status === 401) {
            clearProfile()
            // 过滤不需要登录的页面
            if (!NoAuthPage.includes(location.pathname)) {
                await router.push(`/login${location.search ? '?' + location.search : ''}`);
                return Promise.reject({
                    message: '请先登录',
                    status: response.status
                });
            }
        }

        try {
            const currentRoute = router.currentRoute;
            jka.track('$apiError', {
                pagePath: currentRoute.path,
                pageTitle: currentRoute.meta?.title,
                statusCode: response.status,
                url: response.request.responseURL,
                data: response.request.responseText
            });
            _trackEvent(currentRoute.meta?.title, '接口报错', response.request.responseText)
        } catch (e) {
            // ignore
        }

        return Promise.reject(response.data.message != null
            ? response.data
            : {
                message: `${response.status}：${response.statusText}`,
                status: response.status
            }
        );
    }
    if (error.request) {
        return Promise.reject({message: '网络连接出错'}); // 请求失败（网络错误）
    }
    return Promise.reject({message: error.message}); // 其他错误
});

export function get(path, params) {
    return instance.get(path, {
        params
    });
}

export function post(path, data, config) {
    return instance.post(path, data instanceof FormData ? data : paramsSerializer(data), config);
}

export function postJSON(path, data, config) {
  return instance.post(path, data, {...config, 'Content-Type': 'application/json'});
}


export function postRequest(path, data, config) {
    return instance.post(path, data, config);
}
