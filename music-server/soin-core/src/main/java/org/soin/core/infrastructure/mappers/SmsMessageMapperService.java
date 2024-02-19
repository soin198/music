package org.soin.core.infrastructure.mappers;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.soin.core.domain.sms.entity.SmsMessage;
import org.soin.core.infrastructure.mappers.mapper.sms.SmsMessageMapper;
import org.springframework.stereotype.Service;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:57
 **/
@Service(value = "SmsMessageMapperService")
@SuppressWarnings("all")
public class SmsMessageMapperService extends ServiceImpl<SmsMessageMapper, SmsMessage> implements IService<SmsMessage> {
}
