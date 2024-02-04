package org.soin.core.domain.custom.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.BaseEntity;

import java.io.Serializable;

/**
 * 后台员工表
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 14:39
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5360577733598346678L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

}
