package org.soin.core.domain.sms.repository;


import org.soin.core.domain.sms.entity.SmsMessage;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface ISmsMessageRepository {

    /**
     * 记录短信发送
     *
     * @param userId  发送人员ID
     * @param phone   发送手机号码
     * @param content 发送内容
     * @param state   发送状态
     * @param type    发送类型
     */
    void insert(Long userId, String phone, String content, SmsMessage.State state, SmsMessage.Type type);
}
