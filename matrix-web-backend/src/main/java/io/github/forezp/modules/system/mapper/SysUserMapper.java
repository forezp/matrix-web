package io.github.forezp.modules.system.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {


    IPage<SysUser> searchUsers(Page page, @Param("userId") String userId,
                               @Param("realname") String realname);


    SysUser selectUserRolePermission(@Param("userId") String userId);


}
