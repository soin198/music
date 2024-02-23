package org.soin.client.api.privateApi;

import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 * @apiNote 此Api不可对外暴露，内部调用同步数据
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/private")
public interface ISyncImageApi {


    /**
     * 同步歌手头像图片名称
     *
     * @return 是否同步成功
     * @apiNote 执行顺序（1）
     */
    @PostMapping("/syncImageName")
    GenericResponse<Boolean> syncImageName();


}
