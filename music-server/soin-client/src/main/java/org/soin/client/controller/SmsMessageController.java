package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.ISmsMessageApi;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.service.CustomService;
import org.soin.core.domain.cilentCustom.vo.reception.LoginVo;
import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.domain.sms.serivce.SmsMessageService;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.common.RunTimeTool;
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

    private final CustomService customService;


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

    /**
     * 使用验证码登录
     *
     * @param phone 登录电话号码
     * @param code  验证码
     * @return 登陆相关数据
     */
    @Override
    public GenericResponse<LoginVo> login(String phone, String code, SmsMessage.Type type) {
        RunTimeTool.printMethodMsg("login", "验证码登录", phone, code, type);
        Assert.isBlank(phone, "请输入手机号码");
        Assert.isBlank(code, "请输入验证码");
        Assert.isNull(type, "未知的发送类型");
        boolean condition = smsMessageService.validate(phone, code, type);
        RunTimeTool.printMethodResponseMsg("validate", condition);
        Assert.isTrue(!condition, "验证失败");
        Custom custom = customService.phoneQuery(phone);
        LoginVo login = customService.login(custom.getUsername(), custom.getPassword());
        RunTimeTool.printMethodResponseMsg("login", login);
        return GenericResponse.builder().success(login);
    }
}
