package org.soin.client.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户注册
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-30 17:21
 **/
@Data
public class RegisterForm implements Serializable {

    private static final long serialVersionUID = -3164826060850361503L;

    /**
     * 用户名
     */
    @NotBlank(message = "请提供用户名")
    @Length(max = 19, message = "用户名最大支持19个字符")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "请提供密码")
    @Length(max = 19, message = "密码最多支持19个字符")
    private String password;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 简介
     */
    private String resume;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区/县
     */
    private String region;

}
