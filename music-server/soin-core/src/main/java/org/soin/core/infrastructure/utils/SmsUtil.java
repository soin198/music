package org.soin.core.infrastructure.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 短信发送工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-18 14:07
 **/
@Component
public class SmsUtil {

    @Value("${sms.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.accessSecret}")
    private String accessKeySecret;

    @Value("${sms.templateCode}")
    private String templateCode;

    @Value("${sms.signName}")
    private String signName;

    @Value("${sms.isOpen}")
    private Boolean smsOpen;

    /**
     * 发送手机短信
     *
     * @param phone   发送手机
     * @param content 发送内容
     * @return 是否发送成功
     */
    public boolean messageSend(String phone, String content) {
        Assert.isBlank(content, "请提供发送内容");
        String param = JSONObject.toJSONString(content);
        if (!smsOpen) {
            RunTimeTool.printWarn("发送失败，短信通道暂未开启，保留本地输出");
            return Boolean.FALSE;
        }
        try {
            SendSmsResponseBody send = this.send(phone, param);
            if (!BaseConstant.SMS_SEND_RESULT_CODE.equals(send.getCode())) {
                RunTimeTool.printError(String.format("目标%s短信发送失败，失败原因%s", phone, send.getMessage()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            RunTimeTool.printError(String.format("目标%s短信发送失败", phone));
        }
        return Boolean.TRUE;
    }

    /**
     * 发送短信
     *
     * @param phone 接收手机号
     * @param param json参数字符串
     * @return 短信发送响应体
     * @throws Exception 只要存在一场酒抛出
     */
    private SendSmsResponseBody send(String phone, String param) throws Exception {
        Config config = new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam(param);
        SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, new RuntimeOptions());
        return sendSmsResponse.getBody();
    }

}
