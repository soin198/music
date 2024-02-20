package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.ISmsMessageApi;
import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.domain.sms.serivce.SmsMessageService;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.utils.Assert;
import org.soin.core.infrastructure.utils.RunTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收藏歌曲控制层
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 18:13
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsMessageController implements ISmsMessageApi {

    private final SmsMessageService smsMessageService;

    /**
     * 生成验证码并发送手机
     *
     * @param phone 发送手机号码
     * @param type  短信发送类型
     * @return 是否发送成功
     */
    @Override
    public GenericResponse<Boolean> generateCode(String phone, SmsMessage.Type type) {
        RunTimeTool.printMethodMsg("generateCode", "生成验证码发送手机", phone, type);
        Assert.isBlank(phone, "请输入手机号码");
        Assert.isNull(type, "未知的发送类型");
        boolean isOpen = smsMessageService.generateCode(phone, type);
        RunTimeTool.printMethodResponseMsg("generateCode", isOpen);
        return GenericResponse.builder().success(isOpen);
    }
}
