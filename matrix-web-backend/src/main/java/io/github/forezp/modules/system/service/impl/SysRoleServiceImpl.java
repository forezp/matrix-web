package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.BeanUtils;
import io.github.forezp.modules.system.entity.SysRole;
import io.github.forezp.modules.system.entity.SysUserRole;
import io.github.forezp.modules.system.mapper.SysRoleMapper;
import io.github.forezp.modules.system.mapper.SysUserRoleMapper;
import io.github.forezp.modules.system.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.system.vo.dto.SysRoleDTO;
import io.github.forezp.modules.system.vo.dto.SysUserRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public SysUserRoleDTO getUserRoleDTO(String userId) {

        SysUserRoleDTO sysUserRoleDTO = new SysUserRoleDTO();
        List<Long> selectIds = new ArrayList<>();
        List<SysRoleDTO> roleDTOList = new ArrayList<>();
        List<SysRole> sysRoles = getBaseMapper().selectList(null);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(sysRoles)) {
            for (SysRole sysRole : sysRoles) {
                SysRoleDTO sysRoleDTO = new SysRoleDTO();
                BeanUtils.copy(sysRole, sysRoleDTO);
                for (SysUserRole sysUserRole : sysUserRoleList) {
                    if (sysRole.getRoleId().equals(sysUserRole.getRoleId())) {
                        selectIds.add(sysRole.getId());
                        sysRoleDTO.setChecked(true);
                        break;
                    }

                }
                roleDTOList.add(sysRoleDTO);
            }
        }
        sysUserRoleDTO.setRoleDTOList(roleDTOList);
        sysUserRoleDTO.setSelectIds(selectIds);
        return sysUserRoleDTO;
    }

    @Override
    public PageResultsDTO searchRolePage(int page, int pageSize, String roleId, String name) {
        Page<SysRole> sysRolePage = new Page<>(page, pageSize);
        IPage<SysRole> sysUserIPage = sysRoleMapper.searchRolePage(sysRolePage, roleId, name);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysUserIPage.getTotal());
        result.setTotalPage((int) sysUserIPage.getTotal(), pageSize);
        List<SysRole> records = sysUserIPage.getRecords();
        result.setList(records);
        return result;
    }

    @Override
    public Boolean saveRole(String roleId, String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        SysRole sysRoleDb = sysRoleMapper.selectOne(queryWrapper);
        if (sysRoleDb != null) {
            throw new AriesException(ErrorCode.INSERT_DATA_EXIST);
        }
        SysRole sysRole = new SysRole();
        sysRole.setName(name);
        sysRole.setRoleId(roleId);
        return sysRoleMapper.insert(sysRole) == 1 ? true : false;
    }


}
