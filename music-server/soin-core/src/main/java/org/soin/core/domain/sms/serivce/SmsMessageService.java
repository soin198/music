package org.soin.core.domain.sms.serivce;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.soin.core.domain.cilentCustom.entity.Custom;
import org.soin.core.domain.cilentCustom.repository.ICustomRepository;
import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.domain.sms.repository.ISmsMessageRepository;
import org.soin.core.infrastructure.enums.CommonTimeEnum;
import org.soin.core.infrastructure.enums.RegionEnum;
import org.soin.core.infrastructure.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return 是否发送成功
     */
    public boolean generateCode(String phone) {
        Assert.isBlank(phone, "请输入手机号码");
        boolean mobile = RegexExpressionUtil.isMobile(phone);
        Assert.isTrue(!mobile, "手机号无效");
        Custom custom = iCustomRepository.getUserByPhone(phone);
        Assert.isNull(custom, "手机号未注册，请注册后重新登录");
        String code = RandomStringUtils.randomNumeric(6);
        RunTimeTool.printMethodResponseMsg("generateCode", code);
        Long userId = custom.getId();
        //发送短信
        boolean isOpen = smsUtil.messageSend(phone, code);
        if (isOpen) {
            CacheUtil.put(phone, code, CommonTimeEnum.SECS_300.getSecond(), TimeUnit.SECONDS, RegionEnum.CLIENT);
            iSmsMessageRepository.insert(userId, phone, code, SmsMessage.State.SUCCESS, SmsMessage.Type.LOGIN_CODE);
            return Boolean.TRUE;
        }
        iSmsMessageRepository.insert(userId, phone, code, SmsMessage.State.ERROR, SmsMessage.Type.LOGIN_CODE);
        return Boolean.FALSE;
    }
}
