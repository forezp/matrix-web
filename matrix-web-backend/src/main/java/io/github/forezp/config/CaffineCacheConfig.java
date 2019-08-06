package io.github.forezp.config;

import io.github.forezp.common.cache.caffine.UserRolePermissionCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaffineCacheConfig {
    @Bean
    public UserRolePermissionCache userRolePermissionCache(){
        return new UserRolePermissionCache();
    }
}
