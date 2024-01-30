package org.soin.core.util;

import org.soin.core.biz.common.enums.CacheTypeEnum;
import org.soin.core.biz.common.enums.FolderEnum;
import org.soin.core.cache.CacheNameSpace;
import org.soin.core.cache.cacheApi.ICache;
import org.soin.core.cache.factory.CacheFactoryKit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 缓存工具类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-28 17:36
 **/
public class CacheUtil {

    /**
     * 私有构造函数
     */
    private CacheUtil() {}

    /**
     * 缓解空间工厂
     */
    @Resource
    private CacheFactoryKit cacheFactoryKit;

    /**
     * 单例模式
     */
    private static CacheFactoryKit KIT;

    /**
     * 初始化
     */
    @PostConstruct
    public void build() {
        KIT = cacheFactoryKit;
    }

    /**
     * 保存缓存数据包含过期时间
     *
     * @param key      缓存key
     * @param value    缓存值
     * @param seconds  失效时长
     * @param timeUnit 时间单位
     * @param enums    数据源
     * @return 是否成功
     */
    public static <COMPANY_KEY> boolean put(COMPANY_KEY key, Object value, Long seconds, TimeUnit timeUnit, FolderEnum enums) {
        ICache cache = KIT.getCache(enums);
        CacheNameSpace cacheNameSpace =
                CacheNameSpace
                        .builder()
                        .withCacheNameSpace(CacheTypeEnum.LOGIN.getNameSpace())
                        .withCachePrefix(enums.getNameSpace())
                        .build();
        return cache.put(cacheNameSpace, key, value, seconds, timeUnit);
    }

    /**
     * 保存缓存数据不包含过期时间
     *
     * @param key   缓存key
     * @param value 缓存值
     * @param enums 数据源
     * @return 是否成功
     */
    public static <COMPANY_KEY> boolean put(COMPANY_KEY key, Object value, FolderEnum enums) {
        ICache cache = KIT.getCache(enums);
        CacheNameSpace cacheNameSpace =
                CacheNameSpace
                        .builder()
                        .withCacheNameSpace(CacheTypeEnum.LOGIN.getNameSpace())
                        .withCachePrefix(enums.getNameSpace())
                        .build();
        return cache.put(cacheNameSpace, key, value);
    }

    /**
     * 删除缓存数据
     *
     * @param key   缓存key
     * @param enums 数据源
     * @return 是否成功
     */
    public static <COMPANY_KEY> boolean remove(COMPANY_KEY key, FolderEnum enums) {
        ICache cache = KIT.getCache(enums);
        CacheNameSpace cacheNameSpace =
                CacheNameSpace
                        .builder()
                        .withCacheNameSpace(CacheTypeEnum.LOGIN.getNameSpace())
                        .withCachePrefix(enums.getNameSpace())
                        .build();
        return cache.remove(cacheNameSpace, key);
    }

    /**
     * 幂等
     *
     * @param key      缓存key
     * @param value    缓存值
     * @param seconds  缓存时间
     * @param timeUnit 时间单位
     * @param enums    数据源
     * @return 是否成功
     */
    public static <COMPANY_KEY> boolean idempotent(COMPANY_KEY key, String value, Long seconds, TimeUnit timeUnit, FolderEnum enums) {
        ICache cache = KIT.getCache(enums);
        CacheNameSpace cacheNameSpace =
                CacheNameSpace
                        .builder()
                        .withCacheNameSpace(CacheTypeEnum.LOGIN.getNameSpace())
                        .withCachePrefix(enums.getNameSpace())
                        .build();
        return (cache.containsKey(cacheNameSpace, key) ? Boolean.FALSE : put(key, value, seconds, timeUnit, enums));
    }

    /**
     * 根据key获取redis缓存数据
     *
     * @param key      键
     * @param classes  字节码对象
     * @param fallBack 兜底函数
     * @param seconds  过期时间
     * @param timeUnit 时间单位
     * @param enums    数据源
     * @return 缓存函数
     */
    public static <R_TYPE, COMPANY_KEY> R_TYPE secureGet(COMPANY_KEY key, Class<R_TYPE> classes, Function<COMPANY_KEY, R_TYPE> fallBack, Long seconds, TimeUnit timeUnit, FolderEnum enums) {
        ICache cache = KIT.getCache(enums);
        CacheNameSpace cacheNameSpace =
                CacheNameSpace
                        .builder()
                        .withCacheNameSpace(CacheTypeEnum.LOGIN.getNameSpace())
                        .withCachePrefix(enums.getNameSpace())
                        .build();
        return cache.secureGet(cacheNameSpace, key, classes, fallBack, seconds, timeUnit);
    }

    /**
     * 根据键获取缓存值
     *
     * @param key     键
     * @param classes 值类型
     * @param enums   数据源
     * @return 获取缓存值
     */
    public static <R_TYPE, COMPANY_KEY> R_TYPE byKeyGet(COMPANY_KEY key,
                                                        Class<R_TYPE> classes,
                                                        FolderEnum enums) {
        ICache cache = KIT.getCache(enums);
        CacheNameSpace cacheNameSpace =
                CacheNameSpace
                        .builder()
                        .withCacheNameSpace(CacheTypeEnum.LOGIN.getNameSpace())
                        .withCachePrefix(enums.getNameSpace())
                        .build();
        return cache.byKeyGet(cacheNameSpace, key, classes);
    }

}
