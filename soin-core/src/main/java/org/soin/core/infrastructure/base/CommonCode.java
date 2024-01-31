package org.soin.core.infrastructure.base;

/**
 * 错误码
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-29 10:43
 **/
public class CommonCode {

    /**
     * 没有登录
     */
    public static final int API_CODE_NOT_LOGIN = -201;

    /**
     * 登录失败
     */
    public static final Integer API_CODE_LOGIN_FAIL = 1;

    /**
     * 请求成功
     */
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 请求错误
     */
    public static final Integer ERROR_CODE = 301;

    /**
     * 发送其他错误
     */
    public static final Integer ERROR_CODE_OTHER = 302;

    /**
     * 没有实名
     */
    public static final Integer REAL_NAME_ERROR_CODE = 555;

}
