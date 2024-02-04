package org.soin.core.infrastructure.utils;

import org.springframework.lang.Nullable;

/**
 * 自定义断言工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:07
 **/
public class Assert {

    /**
     * 验证字符串是否为空
     *
     * @param param   参数
     * @param message 错误信息
     */
    public static void isBlank(String param, String message) {
        boolean isOpen = StringUtil.isBlank(param);
        if (isOpen) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 验证对象是否为空
     *
     * @param param   参数
     * @param message 错误信息
     */
    public static void isNull(@Nullable Object param, String message) {
        if (null == param) {
            throw new IllegalArgumentException(message);
        }
    }


}