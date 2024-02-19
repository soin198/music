package org.soin.core.infrastructure.repository.sms;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.domain.sms.repository.ISmsMessageRepository;
import org.soin.core.infrastructure.mappers.mapper.sms.SmsMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsMessageRepository implements ISmsMessageRepository {

    private final SmsMessageMapper smsMessageMapper;

    /**
     * 记录短信发送
     *
     * @param userId  发送人员ID
     * @param phone   发送手机号码
     * @param content 发送内容
     * @param state   发送状态
     * @param type    发送类型
     */
    @Override
    public void insert(Long userId, String phone, String content, SmsMessage.State state, SmsMessage.Type type) {
        SmsMessage message = new SmsMessage();
        message.setUserId(userId);
        message.setPhone(phone);
        message.setContent(content);
        message.setState(state);
        message.setType(type);
        smsMessageMapper.insert(message);
    }
}


