package io.github.forezp.modules.system.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.dto.ValidationResult;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.util.*;
import io.github.forezp.modules.system.entity.SysLoginLog;
import io.github.forezp.modules.system.entity.SysMenu;
import io.github.forezp.modules.system.entity.SysUser;
import io.github.forezp.modules.system.service.SysLoginLogService;
import io.github.forezp.modules.system.service.SysMenuService;
import io.github.forezp.modules.system.service.SysUserService;

import io.github.forezp.modules.system.vo.domain.SysUserAddDomain;
import io.github.forezp.permission.HasPermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.forezp.common.exception.ErrorCode.ERROR_ARGS;
import static io.github.forezp.common.exception.ErrorCode.PWD_ERROR;

import static io.github.forezp.common.exception.ErrorCode.USER_NOT_EXIST;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2018-08-03
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class SysUserController {


    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysLoginLogService sysLoginLogService;

    @GetMapping("/currentUser")
    public RespDTO getCurrenUser() {
        return RespDTO.onSuc(sysUserService.getCurrentUser());
    }

    @GetMapping("/pagelist")
    public RespDTO searchUsers(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String userId, @RequestParam(required = false) String realname) {
        PageUtils.check(page, pageSize);
        PageResultsDTO sysUsers = sysUserService.searchUsers(page, pageSize, userId, realname);
        return RespDTO.onSuc(sysUsers);
    }

    @PostMapping("")
    public RespDTO addUser(@RequestBody SysUserAddDomain sysUserAddDomain) {
        ValidatorUtils.validateEntity(sysUserAddDomain);
        sysUserService.addUser(sysUserAddDomain);
        return RespDTO.onSuc(null);
    }

    @PostMapping("/login")
    public RespDTO login(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", username);
        SysUser user = sysUserService.getOne(queryWrapper);
        if (user == null) {
            //异步存储登陆日志
            saveSysLoginLog(username, null, false);
            throw new AriesException(USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(MD5Utils.encrypt(password))) {
            saveSysLoginLog(username, null, false);
            throw new AriesException(PWD_ERROR);
        }
        //登录成功
        String jwt;
        Map<String, String> result = new HashMap<>(1);
        try {
            jwt = JWTUtils.createJWT(user.getId() + "", user.getUserId(), 599999999L);
            result.put("token", jwt);
            log.info("login success,{}", jwt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //异步存储登陆日志
        saveSysLoginLog(username, user.getRealname(), true);
        return RespDTO.onSuc(result);
    }

    private void saveSysLoginLog(String username, String realname, boolean isLoginSuccess) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIp(HttpUtils.getIpAddress());
        sysLoginLog.setLoginName(username);
        sysLoginLog.setRealname(realname);
        if (isLoginSuccess) {
            sysLoginLog.setStatus(1);
        } else {
            sysLoginLog.setStatus(2);
        }
        sysLoginLog.setLoginTime(new Date());
        sysLoginLogService.saveLoginLog(sysLoginLog);
    }


    @GetMapping("/info")
    public RespDTO info() {

//        String token=null;
//        try {
//             token= HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get("Authorization");
//
//        }catch (Exception e){
//          ExceptionUtils.printRootCauseStackTrace(e);
//        }
//
//        if(StringUtils.isEmpty(token)){
//            throw new AriesException(TOKEN_ISNULL);
//        }
//        try {
//            Claims claims=JWTUtils.parseJWT(token);
//            if(claims!=null){
//                String id=claims.getId();
//                String userId=claims.getSubject();
//
//            }
//        } catch (Exception e) {
//            ExceptionUtils.printRootCauseStackTrace(e);
//        }

        Map<String, Object> result = new HashMap<>();
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ismenu", 1);
        List<SysMenu> menus = sysMenuService.list(queryWrapper);

        log.info("menuList size:" + menus.size());
        result.put("menus", menus);
        result.put("roles", "administrator");

        return RespDTO.onSuc(result);
    }

    @PostMapping("/roles")
    @HasPermission(hasRole = "ROLE_ADMIN")
    public RespDTO userSetRoles(@RequestParam String userId, @RequestParam String roleIds) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleIds)) {
            throw new AriesException(ERROR_ARGS);
        }

        sysUserService.setUserRoles(userId, roleIds);

        return RespDTO.onSuc(null);
    }

}