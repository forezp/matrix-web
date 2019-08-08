package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.system.service.SysRoleService;
import io.github.forezp.modules.system.vo.dto.SysUserRoleDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;


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


    @GetMapping("/pagelist")
    public RespDTO searchUsers(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String roleId, @RequestParam(required = false) String name) {
        PageUtils.check(page, pageSize);
        PageResultsDTO sysUsers = sysRoleService.searchRolePage(page, pageSize, roleId, name);
        return RespDTO.onSuc(sysUsers);
    }

    @PostMapping("")
    public RespDTO saveRole(@RequestParam String roleId, @RequestParam String name) {
        if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(name)) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
        if (sysRoleService.saveRole(roleId, name)) {
            return RespDTO.onSuc(null);
        } else {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
    }

}

