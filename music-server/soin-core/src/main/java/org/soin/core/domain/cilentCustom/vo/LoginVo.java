package org.soin.core.domain.cilentCustom.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 16:14
 **/
@Data
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 362985345149809065L;

    /**
     * 人员ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * token
     */
    private String token;

}
