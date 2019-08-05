package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
}
