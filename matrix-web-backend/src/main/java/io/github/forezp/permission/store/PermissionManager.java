package io.github.forezp.permission.store;

import java.util.List;

/**
 * Created by forezp on 2018/8/6.
 */
public interface PermissionManager {

    /**
     * 从数据库中拖出权限
     * @param userId
     * @return
     */
    List<String> fetch(String userId);

    /**
     * 拖出权限并缓存
     * @param userId
     */
    void fetchAndCache(String userId);

    /**
     * 从PermissionManager获取权限
     * @param userId
     * @return
     */
    List<String> get(String userId);

    /**
     * 缓存权限
     * @param userId
     * @param permissions
     */
    void cache(String userId, List<String> permissions);


    /**
     * 主动使缓存的权限失效
     * @param userId
     */
    void expire(String userId);
}
