package org.soin.core.infrastructure.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 公共入参
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:54
 **/
@Data
public abstract class BaseParams implements Serializable {

    private static final long serialVersionUID = 7949632165890528357L;

    /**
     * 人员ID
     */
    public Long userId;
}
