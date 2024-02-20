import {get, post} from "./request";

const CoreManager = {
    //账号密码登录
    login: (params) => post(`/custom/authority/login`, params),
    //注册
    register: (params) => post(`/custom/authority/register`, params),
    //注销账户
    cancelAccount: (userId) => post(`/custom/authority/cancel?userId=${userId}`),
    //获取单个用户
    getOne: (userId) => get(`/custom/authority/detailsQuery?userId=${userId}`)
};

export {CoreManager};
