package io.github.forezp.modules.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.ThreadPoolFactory;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.BeanUtils;
import io.github.forezp.common.util.MD5Utils;
import io.github.forezp.common.util.UserUtils;
import io.github.forezp.modules.system.entity.*;
import io.github.forezp.modules.system.mapper.SysRoleMapper;
import io.github.forezp.modules.system.mapper.SysUserMapper;
import io.github.forezp.modules.system.mapper.SysUserOrgMapper;
import io.github.forezp.modules.system.mapper.SysUserRoleMapper;
import io.github.forezp.modules.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.system.vo.domain.SysUserAddDomain;
import io.github.forezp.modules.system.vo.dto.SysUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

import static io.github.forezp.common.constant.CommonConstants.*;
import static io.github.forezp.common.constant.ThreadPoolConstants.LOG_THREADPOOL;

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

    @Autowired
    SysUserOrgMapper sysUserOrgMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    public PageResultsDTO searchUsers(int page, int pageSize, String userId, String realname) {
        Page<SysUser> sysLogPage = new Page<>(page, pageSize);
        IPage<SysUser> sysUserIPage = sysUserMapper.searchUsers(sysLogPage, userId, realname);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysUserIPage.getTotal());
        result.setTotalPage((int) sysUserIPage.getTotal(), pageSize);
        List<SysUser> records = sysUserIPage.getRecords();
        result.setList(transformSysUserDTO(records));
        return result;

    }

    private List<SysUserDTO> transformSysUserDTO(List<SysUser> records) {
        if (records == null) {
            return null;
        }
        List<SysUserDTO> list = new ArrayList<>();
        for (SysUser sysUser : records) {
            SysUserDTO sysUserDTO = new SysUserDTO();
            BeanUtils.copy(sysUser, sysUserDTO);
            if (sysUserDTO.getSex() == 1) {
                sysUserDTO.setSexName(MALE);
            } else if (sysUserDTO.getSex() == 2) {
                sysUserDTO.setSexName(FEMALE);
            } else {
                sysUserDTO.setSexName(UNKNOWN);
            }
            String roleNames = "";
            if (sysUser.getRoles() != null && sysUser.getRoles().size() > 0) {
                for (SysRole sysRole : sysUser.getRoles()) {
                    roleNames = roleNames + sysRole.getName() + ",";
                }
                if (roleNames.endsWith(",")) {
                    roleNames = roleNames.substring(0, roleNames.length() - 1);
                }
            }
            sysUserDTO.setRoleName(roleNames);
            String orgNames = "";
            if (sysUser.getOrgs() != null && sysUser.getOrgs().size() > 0) {
                for (SysOrg sysOrg : sysUser.getOrgs()) {
                    orgNames = orgNames + sysOrg.getSimpleName() + ",";
                }
                if (orgNames.endsWith(",")) {
                    orgNames = orgNames.substring(0, orgNames.length() - 1);
                }
            }
            sysUserDTO.setOrgName(orgNames);
            list.add(sysUserDTO);
        }
        return list;
    }

    @Override
    public SysUser login(String username, String password) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", username);
        List<SysUser> userList = sysUserMapper.selectList(queryWrapper);
        if (userList == null || userList.size() == 0) {
            throw new AriesException(ErrorCode.USER_NOT_EXIST);
        }
        SysUser sysUser = userList.get(0);

        if (sysUser.getPassword().equals(MD5Utils.encrypt(password))) {

        }

        return null;
    }

    @Override
    public void addUser(SysUserAddDomain sysUserAddDomain) {
        SysUser sysUser = new SysUser();
        BeanUtils.copy(sysUserAddDomain, sysUser);
        sysUserMapper.insert(sysUser);
        SysUserOrg sysUserOrg = new SysUserOrg();
        BeanUtils.copy(sysUserAddDomain, sysUserOrg);
        sysUserOrgMapper.insert(sysUserOrg);
    }

    @Override
    public void setUserRoles(String userId, String roleId) {
        String[] ids = roleId.split(COMMA);
        for (String id : ids) {
            SysRole sysRole = sysRoleMapper.selectById(id);
            if (sysRole != null) {
                QueryWrapper queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id", userId);
                queryWrapper.eq("role_id", sysRole.getRoleId());
                SysUserRole sysUserRoleDB = sysUserRoleMapper.selectOne(queryWrapper);
                if (sysUserRoleDB == null) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(sysRole.getRoleId());
                    sysUserRole.setStatus(1);
                    sysUserRoleMapper.insert(sysUserRole);
                }
            }
        }
    }

    @Override
    public SysUser getCurrentUser() {
        String userId = UserUtils.getCurrentUser();
        if (StringUtils.isEmpty(userId)) {
            throw new AriesException(ErrorCode.FAIL);
        }
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }

    @Override
    public SysUser getUserById(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new AriesException(ErrorCode.FAIL);
        }
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;

    }
}
