package io.github.forezp.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.forezp.modules.system.entity.SysMenu;

import io.github.forezp.modules.system.vo.dto.SysMenuDTO;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author forezp
 * @since 2019-07-16
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenuDTO> generateMenuTree(List<SysMenu> menus);

}
