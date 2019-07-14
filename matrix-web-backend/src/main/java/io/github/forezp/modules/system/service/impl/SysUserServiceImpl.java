package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.MD5Utils;
import io.github.forezp.modules.system.entity.SysUser;
import io.github.forezp.modules.system.mapper.SysUserMapper;
import io.github.forezp.modules.system.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    SysUserMapper sysUserMapper;

    public PageResultsDTO searchUsers(int page, int pageSize) {

        Map paramMap = new HashMap<>();
        Page<SysUser> userPage = new Page<>( page, pageSize );
//
//        if (StringUtils.isNotBlank(orgId)) {
//            paramMap.put("orgId", orgId);
//        }
//        if (StringUtils.isNotBlank(realname)) {
//            paramMap.put("realname", realname);
//        }
//        if (StringUtils.isNotBlank(roleName)) {
//            paramMap.put("roleName", roleName);
//        }
//        if (StringUtils.isNotBlank(orgName)) {
//            paramMap.put("orgName", orgName);
//        }
//
//        if (StringUtils.isNotBlank(roleId)) {
//            paramMap.put("roleId", roleId);
//        }

        //    paramMap.put("status", status);

        List<SysUser> sysUsers = sysUserMapper.searchUsers( userPage, paramMap );
        PageResultsDTO result = new PageResultsDTO( page, pageSize );
        result.setTotalCount( userPage.getTotal() );
        result.setList( sysUsers );
        return result;

    }

    @Override
    public SysUser login(String username, String password) {

        List<SysUser> userList = sysUserMapper.selectList( Condition.create().eq( "username", username ) );
        if (userList == null || userList.size() == 0) {
            throw new AriesException( ErrorCode.USER_NOT_EXIST );
        }
        SysUser sysUser=userList.get( 0 );

        if(sysUser.getPassword().equals( MD5Utils.encrypt( password ) )){

        }

        return null;
    }
}
