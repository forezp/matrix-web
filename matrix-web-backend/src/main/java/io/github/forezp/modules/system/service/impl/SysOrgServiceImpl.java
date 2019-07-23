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
     *
     * @param sysOrgs
     * @param
     * @return
     */
    public List<SysOrgDTO> treeSort(List<SysOrg> sysOrgs, String pid, int depth) {

        if (null != sysOrgs && sysOrgs.size() > 0) {
            List<SysOrgDTO> sysOrgDTOs = new ArrayList<>();
            for (SysOrg sysOrg : sysOrgs) {
                SysOrgDTO sysOrgDTO = new SysOrgDTO();

                sysOrgDTO.setDepth(depth);


                BeanUtils.copyProperties(sysOrg, sysOrgDTO);

                if (pid.equals(sysOrg.getPid())) {
                    sysOrgDTO.setChildren(treeSort(sysOrgs, sysOrg.getOrgId(), depth + 1));
                    sysOrgDTOs.add(sysOrgDTO);
                }

            }

            return sysOrgDTOs;
        }
        return null;
    }

}
