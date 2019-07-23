package io.github.forezp.modules.system.service.impl;

import io.github.forezp.modules.system.entity.SysPermission;
import io.github.forezp.modules.system.mapper.SysPermissionMapper;
import io.github.forezp.modules.system.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author forezp
 * @since 2018-08-02
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {


    @Override
    public List<String> selectUserPermissons(String userId) {
       return this.baseMapper.selectUserPermissons(userId);
    }
}
