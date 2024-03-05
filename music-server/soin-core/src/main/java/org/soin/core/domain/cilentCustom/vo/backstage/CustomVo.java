package org.soin.core.domain.cilentCustom.vo.backstage;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 16:14
 **/
@Data
public class CustomVo implements Serializable {

    private static final long serialVersionUID = 6634226754131954187L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String photo;

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

}
