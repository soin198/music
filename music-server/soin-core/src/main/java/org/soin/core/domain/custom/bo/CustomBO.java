package org.soin.core.domain.custom.bo;

import lombok.Data;
import org.soin.core.domain.custom.entity.Custom;

import java.io.Serializable;
import java.util.Date;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 16:14
 **/
@Data
public class CustomBO implements Serializable {

    private static final long serialVersionUID = 6634226754131954187L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Custom.GenderEnum sex;

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
