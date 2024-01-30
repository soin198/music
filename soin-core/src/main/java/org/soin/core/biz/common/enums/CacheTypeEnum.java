package org.soin.core.biz.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缓存命名空间枚举值
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-26 14:16
 **/
@Getter
@AllArgsConstructor
public enum CacheTypeEnum {

    /**
     * 登录命名空间
     */
    LOGIN("LOGIN", "LOGIN:"),

    /**
     * 短信发送命名空间
     */
    MESSAGE("SMS", "SMS:");

    /**
     * 枚举code
     */
    private final String code;

    /**
     * 命名空间
     */
    private final String nameSpace;


}
