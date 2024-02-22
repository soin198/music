package org.soin.core.infrastructure.repository.sms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.domain.sms.repository.ISmsMessageRepository;
import org.soin.core.infrastructure.mapper.sms.SmsMessageMapper;
import org.soin.core.infrastructure.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;


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

    /**
     * 判断当前手机号x秒内是否存在
     *
     * @param phone     发送手机号码
     * @param condition 时间条件
     * @return 是否存在记录
     */
    @Override
    public boolean recentQuery(String phone, int condition) {
        Assert.isBlank(phone, "请提供电话号码");
        Date now = new Date();
        int millis = condition * 1000;
        Date before = new Date(now.getTime() - millis);
        QueryWrapper<SmsMessage> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        wrapper.between("createDate", before, now);
        return smsMessageMapper.exists(wrapper);
    }

    /**
     * 获取短信发送记录
     *
     * @param phone 电话号码
     * @param code  验证码
     * @return 短信发送记录
     */
    @Override
    public SmsMessage phoneAndCodeQuery(String phone, String code) {
        Assert.isBlank(phone, "请提供手机号码");
        Assert.isBlank(code, "请提供验证码");
        QueryWrapper<SmsMessage> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        wrapper.eq("content", code);
        wrapper.orderByDesc("createDate");
        return smsMessageMapper.selectList(wrapper).stream().findFirst().orElse(null);
    }
}



