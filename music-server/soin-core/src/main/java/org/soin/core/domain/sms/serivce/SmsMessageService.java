package org.soin.core.domain.sms.serivce;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.repository.ICustomRepository;
import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.domain.sms.repository.ISmsMessageRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.RegionEnum;
import org.soin.core.infrastructure.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-04 13:05
 **/
@Service(value = "SmsMessageService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsMessageService {

    private final ISmsMessageRepository iSmsMessageRepository;

    private final ICustomRepository iCustomRepository;

    private final SmsUtil smsUtil;

    /**
     * 生成验证码并发送手机
     *
     * @param phone 发送手机号码
     * @param type  短信发送类型
     * @return 是否发送成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean generateCode(String phone, SmsMessage.Type type) {
        Assert.isBlank(phone, "请输入手机号码");
        Assert.isNull(type, "请提供短信发送类型");
        boolean mobile = RegexExpressionUtil.isMobile(phone);
        Assert.isTrue(!mobile, "手机号无效");
        Custom custom = iCustomRepository.getUserByPhone(phone);
        Assert.isNull(custom, "手机号未注册，请注册后重新登录");
        boolean isExist = iSmsMessageRepository.recentQuery(phone, 60);
        Assert.isTrue(isExist, "60秒之内只能发送一条");
        String code = RandomStringUtils.randomNumeric(6);
        RunTimeTool.printMethodResponseMsg("generateCode", code);
        Long userId = custom.getId();
        boolean isOpen = smsUtil.messageSend(phone, code);
        SmsMessage.State state = isOpen ? SmsMessage.State.SUCCESS : SmsMessage.State.ERROR;
        iSmsMessageRepository.insert(userId, phone, code, state, type);
        //return CacheUtil.put(phone, code, CommonTimeEnum.SECS_300.getSecond(), TimeUnit.SECONDS, RegionEnum.CLIENT);
        return isOpen ? CacheUtil.put(phone, code, CommonTimeEnum.SECS_300.getSecond(), TimeUnit.SECONDS, RegionEnum.CLIENT) : Boolean.FALSE;
    }

    /**
     * 验证短信验证码是否可用
     *
     * @param phone 电话号码
     * @param code  验证码
     * @param type  短信类型
     * @return 短信验证码是否可用
     */
    public boolean validate(String phone, String code, SmsMessage.Type type) {
        Assert.isBlank(phone, "请提供手机号码");
        Assert.isBlank(code, "请提供验证码");
        Assert.isNull(type, "短信类型为空");
        boolean mobile = RegexExpressionUtil.isMobile(phone);
        Assert.isTrue(!mobile, "手机号无效");
        Custom custom = iCustomRepository.getUserByPhone(phone);
        Assert.isNull(custom, "手机号未注册，请注册后重新登录");
        SmsMessage smsMessage = iSmsMessageRepository.phoneAndCodeQuery(phone, code);
        Assert.isNull(smsMessage, "验证码无效");
        Assert.isTrue(Objects.equals(SmsMessage.State.ERROR, smsMessage.getState()), "验证码无效，请重新获取验证码");
        String redisCode = CacheUtil.byKeyGet(phone, String.class, RegionEnum.CLIENT);
        Assert.isBlank(redisCode, "验证码过期，请重新获取");
        Assert.isTrue(!Objects.equals(redisCode, code), "验证码错误，请重试输入");
        return Boolean.TRUE;
    }
}
