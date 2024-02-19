package org.soin.core.domain.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 短信发送记录
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-18 16:20
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_sms_message")
public class SmsMessage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -9185904477226961833L;

    /**
     * 发送人员ID
     */
    private Long userId;

    /**
     * 发送手机号码
     */
    private String phone;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送状态
     */
    private State state;

    /**
     * 发送类型
     */
    private Type type;

    /**
     * 发送状态
     */
    public enum State {

        /**
         * 成功
         */
        SUCCESS,

        /**
         * 失败
         */
        ERROR;

    }

    /**
     * 发送类型
     */
    public enum Type {

        /**
         * 登录验证码
         */
        LOGIN_CODE;

    }

}
