package io.github.forezp.modules.system.service;

import io.github.forezp.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据用户名获取用户的所有权限
     * @param userId
     * @return
     */
    List<String> selectUserPermissons(String userId);

}
