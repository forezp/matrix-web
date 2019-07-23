package io.github.forezp.modules.system.mapper;

import io.github.forezp.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户名获取用户的所有权限
     * @param userId
     * @return
     */
    List<String> selectUserPermissons(String userId);

}
