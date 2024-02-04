package org.soin.client.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.soin.core.domain.custom.entity.Custom;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class RegisterDTO implements Serializable {

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
    @NotNull(message = "请选择性别")
    private Custom.GenderEnum sex;

    /**
     * 电话号码
     */
    @NotBlank(message = "请输入您的电话号码")
    @Length(max = 11, message = "电话号码最多支持11个字符")
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "请输入您的邮箱")
    private String email;

    /**
     * 生日
     */
    @NotNull(message = "请选择您的生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 简介
     */
    private String resume;

    /**
     * 省份
     */
    private Integer province;

    /**
     * 城市
     */
    private Integer city;

    /**
     * 区/县
     */
    private Integer region;

}
