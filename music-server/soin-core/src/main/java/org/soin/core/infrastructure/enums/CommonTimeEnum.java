package org.soin.core.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * 时间枚举
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-30 16:35
 **/
@Getter
@AllArgsConstructor
public enum CommonTimeEnum {

    /**
     * 30秒
     */
    SECS_30(30L, TimeUnit.SECONDS),

    /**
     * 30秒
     */
    SECS_60(60L, TimeUnit.SECONDS),

    /**
     * 2分钟
     */
    SECS_120(120L, TimeUnit.SECONDS),

    /**
     * 5分钟
     */
    SECS_240(300L, TimeUnit.SECONDS),

    /**
     * 30分钟
     */
    SECS_300(1800L, TimeUnit.SECONDS),

    /**
     * 1小时
     */
    SECS_1200(3200L, TimeUnit.SECONDS);

    /**
     * 时间
     */
    private final Long second;

    /**
     * 时间单位
     */
    private final TimeUnit unit;

}
