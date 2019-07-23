package io.github.forezp.modules.system.service;

import io.github.forezp.modules.system.entity.SysOrg;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.forezp.modules.system.vo.dto.SysOrgDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author forezp
 * @since 2019-07-21
 */
public interface SysOrgService extends IService<SysOrg> {


     List<SysOrgDTO> listOrgTree();

}
