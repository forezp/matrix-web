package io.github.forezp.modules.system.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.forezp.modules.system.vo.dto.SysUserRoleDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
public interface SysRoleService extends IService<SysRole> {


    SysUserRoleDTO getUserRoleDTO(String userId);

    PageResultsDTO searchRolePage(int page, int pageSize, String userid, String realname);

    Boolean saveRole(String roleId, String name);
}
