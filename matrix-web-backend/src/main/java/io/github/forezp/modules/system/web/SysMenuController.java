package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.system.entity.SysMenu;
import io.github.forezp.modules.system.service.SysMenuService;
import io.github.forezp.modules.system.vo.domain.SysMenuAddDomain;
import io.github.forezp.modules.system.vo.dto.SysMenuDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-07-17
 */
@RestController
public class SysMenuController {


    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/sysMenu/list")
    public RespDTO getMenuList() {
        List<SysMenu> menus = sysMenuService.list(null);
        List<SysMenuDTO> treeMenu= sysMenuService.generateMenuTree(menus);
        return RespDTO.onSuc(treeMenu);
    }

    @PostMapping("/sysMenu")
    public RespDTO addMenu(@RequestBody SysMenuAddDomain sysMenuAdd){

        SysMenu sysMenu=new SysMenu();
        BeanUtils.copyProperties(sysMenuAdd,sysMenu);
        if(sysMenuService.save(sysMenu)){
            return RespDTO.onSuc(null);
        }else {
            throw new AriesException(ErrorCode.INSERT_DATA_FAIL);
        }

    }

    @DeleteMapping("/sysMenu")
    public RespDTO deleteMenu(Long id){
        if(sysMenuService.removeById(id)){
            return RespDTO.onSuc(null);
        }else {

        } throw new AriesException(ErrorCode.INSERT_DATA_FAIL);
    }

}

