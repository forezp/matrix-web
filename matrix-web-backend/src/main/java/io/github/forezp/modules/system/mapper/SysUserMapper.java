package io.github.forezp.modules.system.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import io.github.forezp.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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


    List<SysUser> searchUsers(Page<SysUser> page, Map paramMap);


}
