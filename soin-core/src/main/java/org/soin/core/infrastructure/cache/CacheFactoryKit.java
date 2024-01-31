package org.soin.core.infrastructure.cache;

import org.soin.core.infrastructure.enums.FolderEnum;
import org.soin.core.infrastructure.cache.cacheApi.ICache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * INSTANCE ENTITY
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-28 16:38
 **/
@Component
public class CacheFactoryKit {

    @Resource
    private ICache iCache;

    /**
     * 之前的beforeIndex
     */
    private static int beforeIndex = 0;

    /**
     * 当前缓存空间
     */
    private static int currentIndex = 0;

    /**
     * 处理数据源DB
     *
     * @param enums 数据源
     * @return ICache实例
     */
    public ICache getCache(FolderEnum enums) {
        switch (enums) {
            case CLIENT:
                currentIndex = 0;
                break;
            case MANAGE:
                currentIndex = 1;
                break;
            default:
                throw new IllegalArgumentException("redis数据源不存在");
        }
        return iCache;
    }

    /**
     * 获取当前缓存空间索引
     *
     * @return
     */
    public static int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * 获取之前的连接索引
     *
     * @return 之前的连接索引
     */
    public static int getBeforeIndex() {
        return beforeIndex;
    }

    /**
     * 设置之前的连接索引
     */
    public static void setBeforeIndex(int index) {
        beforeIndex = index;
    }
}
