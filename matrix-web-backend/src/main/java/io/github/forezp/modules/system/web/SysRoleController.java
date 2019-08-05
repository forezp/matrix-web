package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.system.service.SysRoleService;
import io.github.forezp.modules.system.vo.dto.SysUserRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-07-17
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @GetMapping("/{userId}")
    public RespDTO getUserRoleList(@PathVariable(value = "userId") String userId) {

        SysUserRoleDTO sysUserRoleDTO = sysRoleService.getUserRoleDTO(userId);

        return RespDTO.onSuc(sysUserRoleDTO);

    }

}

