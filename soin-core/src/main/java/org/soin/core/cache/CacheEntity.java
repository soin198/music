package org.soin.core.cache;

import lombok.Data;

/**
 * 缓存实体
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-28 17:42
 **/
@Data
public class CacheEntity {

    /**
     * 数据源
     */
    private Object data;

    /**
     * 过期时间
     */
    private Long expireTime;
}
