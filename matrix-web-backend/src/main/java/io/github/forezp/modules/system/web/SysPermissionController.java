package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.system.service.SysPermissionService;
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
 * @since 2018-08-03
 */
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

    @Autowired
    SysPermissionService sysPermissionService;

    @GetMapping("/list/{userId}")
    public RespDTO getUserPermissons(@PathVariable String userId) {

        return RespDTO.onSuc(sysPermissionService.selectUserPermissons(userId));
    }

}

