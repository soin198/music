package org.soin.core.infrastructure.cache.cacheApi.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.soin.core.infrastructure.cache.CacheNameSpace;
import org.soin.core.infrastructure.cache.cacheApi.ICache;
import org.soin.core.infrastructure.utils.JsonUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 缓存实现类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-28 16:40
 **/
@Slf4j
@Component
public class CompanyCacheImpl implements ICache {

    /**
     * redis客户端
     */
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 添加缓存
     *
     * @param cacheNameSpace 缓存命名空间
     * @param key            键
     * @param value          值
     * @return 是否缓存成功
     */
    @Override
    public <T> boolean put(CacheNameSpace cacheNameSpace, T key, Object value) {
        if (null == key || value == null) {
            return Boolean.FALSE;
        }
        String cacheDirName = cacheNameSpace.getCacheNameSpace();
        String cacheKey = cacheDirName + key;
        redisTemplate.opsForValue().set(cacheKey, JsonUtil.byObjectToJson(value));
        return Boolean.TRUE;
    }

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
    @Override
    public <T> boolean put(CacheNameSpace cacheNameSpace, T key, Object value, Long seconds, TimeUnit timeUnit) {
        if (null == key || value == null) {
            return Boolean.FALSE;
        }
        String cacheDirName = cacheNameSpace.getCacheNameSpace();
        String cacheKey = cacheDirName + key;
        redisTemplate.opsForValue().set(cacheKey, JsonUtil.byObjectToJson(value), seconds, timeUnit);
        return Boolean.TRUE;
    }

    /**
     * 删除缓存数据
     *
     * @param key 缓存key
     * @return 是否成功
     */
    @Override
    public <T> boolean remove(CacheNameSpace cacheNameSpace, T key) {
        if (null == key) {
            return Boolean.FALSE;
        }
        String cacheDirName = cacheNameSpace.getCacheNameSpace();
        String cacheKey = cacheDirName + key;
        redisTemplate.delete(cacheKey);
        return Boolean.TRUE;
    }

    /**
     * 验证是否包含此key
     *
     * @param cacheNameSpace 缓存命名空间
     * @param key            键
     * @return 是否包含
     */
    @Override
    public <T> boolean containsKey(CacheNameSpace cacheNameSpace, T key) {
        if (null == key) {
            return Boolean.FALSE;
        }
        String cacheDirName = cacheNameSpace.getCacheNameSpace();
        String cacheKey = cacheDirName + key;
        String cacheVal = redisTemplate.opsForValue().get(cacheKey);
        return StringUtils.isNotBlank(cacheVal);
    }

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
    @Override
    public <R, T> R secureGet(CacheNameSpace cacheNameSpace, T key, Class<R> classes, Function<T, R> fallBack, Long seconds, TimeUnit timeUnit) {
        if (null == key) {
            return null;
        }
        String cacheDirName = cacheNameSpace.getCacheNameSpace();
        String cacheKey = cacheDirName + key;
        String cacheVal = redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isNotBlank(cacheVal)) {
            return JsonUtil.byStringToObject(cacheVal, classes);
        }
        if (null != cacheVal) {
            return null;
        }
        R r = null;
        if (null != fallBack) {
            r = fallBack.apply(key);
        }
        //给定默认值，防止缓存穿透
        if (null == r) {
            redisTemplate.opsForValue().set(cacheKey, "", seconds, timeUnit);
            return null;
        }
        boolean put = this.put(cacheNameSpace, key, r, seconds, timeUnit);
        log.info("redis更新成功，响应值{}", put);
        return r;
    }

    /**
     * 根据键获取缓存值
     *
     * @param cacheNameSpace 命名空间
     * @param key            键
     * @param classes        值类型
     * @return 获取缓存值
     */
    @Override
    public <R, T> R byKeyGet(CacheNameSpace cacheNameSpace, T key, Class<R> classes) {
        String cacheDirName = cacheNameSpace.getCacheNameSpace();
        String cacheKey = cacheDirName + key;
        String cacheVal = redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isBlank(cacheKey)) {
            return null;
        }
        return JsonUtil.byStringToObject(cacheVal, classes);
    }

}
