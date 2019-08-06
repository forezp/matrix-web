package io.github.forezp.common.cache.caffine;


import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;


import java.util.concurrent.TimeUnit;

public class UserRolePermissionCache<SysUser> extends AbstractCaffineCache {

    @Override
    LoadingCache createLoadingCache() {

        LoadingCache loadingCache = Caffeine.newBuilder()
                .expireAfterWrite(10 , TimeUnit.MINUTES)
                .initialCapacity(10)
                .maximumSize(99999999)
                .recordStats()
                .build(new CacheLoader<String, SysUser>() {
                    @Override
                    public SysUser load(String key) throws Exception {
                        return null;
                    }
                });
        return loadingCache;
    }
}