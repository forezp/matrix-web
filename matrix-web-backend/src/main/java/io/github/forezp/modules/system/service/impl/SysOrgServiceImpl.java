package io.github.forezp.modules.system.service.impl;

import io.github.forezp.common.constant.CommonConstants;
import io.github.forezp.modules.system.entity.SysOrg;
import io.github.forezp.modules.system.mapper.SysOrgMapper;
import io.github.forezp.modules.system.service.SysOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.system.vo.dto.SysOrgDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-07-21
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements SysOrgService {


    @Override
    public List<SysOrgDTO> listOrgTree() {

        List<SysOrg> sysOrgs = list(null);
        List<SysOrgDTO> list = treeSort(sysOrgs, CommonConstants.TREE_TOP_PARENT_ID, 1);

        return list;
    }

    /**
     * 递归遍历树
     * @param sysOrgs 菜单编号列表
     * @param pid 当前深度的父节点(从0开始)
     * @param depth 当前深度(从1开始)
     * @return
     */
    public List<SysOrgDTO> treeSort(List<SysOrg> sysOrgs, String pid, int depth) {

        if (null != sysOrgs && sysOrgs.size() > 0) {
            List<SysOrgDTO> sysOrgDTOs = new ArrayList<>();
            for (SysOrg sysOrg : sysOrgs) {
                SysOrgDTO sysOrgDTO = new SysOrgDTO();

                sysOrgDTO.setDepth(depth);

                // 把sysOrg的属性值拷贝给sysOrgDTO
                BeanUtils.copyProperties(sysOrg, sysOrgDTO);

                // 当前节点的父节点与传入的父节点相等，说明当前节点属于目前要找的那一层的节点
                if (pid.equals(sysOrg.getPid())) {
                    // 把当前菜单编号设置为下一级父节点，深度+1
                    sysOrgDTO.setChildren(treeSort(sysOrgs, sysOrg.getOrgId(), depth + 1));
                    sysOrgDTOs.add(sysOrgDTO);
                }

            }

            return sysOrgDTOs;
        }
        return null;
    }

}
