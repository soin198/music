package org.soin.core.domain.manageEmployee.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-03-04 13:05
 **/
@Data
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 3804642625827166852L;

    /**
     * 员工ID
     */
    private Long employeeId;

    /**
     * token
     */
    private String token;
}
