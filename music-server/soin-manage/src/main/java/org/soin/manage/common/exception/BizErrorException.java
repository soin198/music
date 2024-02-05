package org.soin.manage.common.exception;

import java.io.Serializable;

/**
 * 运行时异常类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-29 14:16
 **/
public class BizErrorException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4520876402967883856L;

    public BizErrorException(String msg) {
        super(msg);
    }

    public BizErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
