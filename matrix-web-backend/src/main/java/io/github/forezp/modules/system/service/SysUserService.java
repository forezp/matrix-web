package io.github.forezp.modules.system.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.system.entity.SysLoginLog;
import io.github.forezp.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.forezp.modules.system.vo.domain.SysUserAddDomain;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
public interface SysUserService extends IService<SysUser> {

    PageResultsDTO searchUsers(int page, int pageSize, String userid, String realname);


    SysUser login(String username, String password);

    void addUser(SysUserAddDomain sysUserAddDomain);

    void setUserRoles(String userId,String roleId);

    SysUser getCurrentUser();

    SysUser getUserById(String userId);



}
