package org.soin.core.infrastructure.cache.cacheApi;

import org.soin.core.infrastructure.cache.CacheNameSpace;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * icache 实现
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-28 16:40
 **/
public interface ICache {

    /**
     * 添加缓存
     *
     * @param cacheNameSpace 缓存命名空间
     * @param key            键
     * @param value          值
     * @return 是否缓存成功
     */
    <T> boolean put(CacheNameSpace cacheNameSpace, T key, Object value);

    /**
     * 添加缓存
     *
     * @param cacheNameSpace 缓存命名空间
     * @param key            键
     * @param value          值
     * @param seconds        过期时间
     * @param timeUnit       时间单位
     * @return 是否缓存成功
     */
    <T> boolean put(CacheNameSpace cacheNameSpace, T key, Object value, Long seconds, TimeUnit timeUnit);

    /**
     * 删除缓存数据
     *
     * @param cacheNameSpace 缓存命名空间
     * @param key            缓存key
     * @return 是否成功
     */
    <T> boolean remove(CacheNameSpace cacheNameSpace, T key);

    /**
     * 验证是否包含此key
     *
     * @param cacheNameSpace 缓存命名空间
     * @param key            键
     * @return 是否包含
     */
    <T> boolean containsKey(CacheNameSpace cacheNameSpace, T key);

    /**
     * 根据K获取缓存数据
     *
     * @param cacheNameSpace 命名空间名称
     * @param key            键
     * @param classes        字节码类型
     * @param fallBack       兜底函数
     * @param seconds        过期时间
     * @param timeUnit       时间单位
     * @return 缓存数据
     */
    <R, T> R secureGet(CacheNameSpace cacheNameSpace, T key, Class<R> classes, Function<T, R> fallBack, Long seconds, TimeUnit timeUnit);

    /**
     * 根据键获取缓存值
     *
     * @param cacheNameSpace 命名空间
     * @param key            键
     * @param classes        值类型
     * @return 获取缓存值
     */
    <R, T> R byKeyGet(CacheNameSpace cacheNameSpace, T key, Class<R> classes);
}
