package org.soin.core.domain.cilentCustom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户信息
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:11
 **/
@Data
@TableName(value = "t_custom")
@EqualsAndHashCode(callSuper = true)
public class Custom extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6176882420433792109L;

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
    private GenderEnum sex;

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
    private LocalDate birth;

    /**
     * 个性签名
     */
    private String resume;

    /**
     * 性别枚举
     */
    public enum GenderEnum {

        /**
         * 男性
         */
        MALE,

        /**
         * 女性
         */
        WOMEN,

        /**
         * 其他
         */
        OTHER;
    }
}
