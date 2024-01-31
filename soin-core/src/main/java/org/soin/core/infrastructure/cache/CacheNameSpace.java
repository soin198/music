package org.soin.core.infrastructure.cache;

/**
 * 缓存命名空间
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-28 17:32
 **/
public class CacheNameSpace {

    /**
     * 缓存命名空间
     */
    private final String cacheNameSpace;

    private CacheNameSpace(String cacheNameSpace, String cachePrefix) {
        this.cacheNameSpace = cachePrefix + cacheNameSpace;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 获取缓存命名空间
     *
     * @return 命名空间
     */
    public String getCacheNameSpace() {
        return cacheNameSpace;
    }

    /**
     * 构造器
     */
    public static class Builder {

        private String cacheNameSpace;

        private String cachePrefix;

        public Builder withCacheNameSpace(String cacheNameSpace) {
            this.cacheNameSpace = cacheNameSpace;
            return this;
        }

        public Builder withCachePrefix(String cachePrefix) {
            this.cachePrefix = cachePrefix;
            return this;
        }

        public CacheNameSpace build() {
            return new CacheNameSpace(cacheNameSpace, cachePrefix);
        }
    }
}
