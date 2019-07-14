package io.github.forezp.modules.system.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
public interface SysUserService extends IService<SysUser> {

    PageResultsDTO searchUsers(int page, int pageSize);

    SysUser login(String username, String password);

}
