package io.github.forezp.modules.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.forezp.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRole> searchRolePage(Page page, @Param("roleId") String roleId,
                               @Param("name") String name);

}
