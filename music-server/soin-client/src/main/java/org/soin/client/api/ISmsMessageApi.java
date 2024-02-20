package org.soin.client.api;

import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/sms")
public interface ISmsMessageApi {

    /**
     * 生成验证码并发送手机
     *
     * @param phone 发送手机号码
     * @param type  短信发送类型
     * @return 是否发送成功
     */
    @PostMapping("/generateCode")
    GenericResponse<Boolean> generateCode(@RequestParam(value = "phone") String phone,
                                          @RequestParam(value = "type") SmsMessage.Type type);





}
