package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.BeanUtils;
import io.github.forezp.modules.system.entity.SysOrg;
import io.github.forezp.modules.system.service.SysOrgService;
import io.github.forezp.modules.system.vo.domain.SysOrgAddDomain;
import io.github.forezp.modules.system.vo.dto.SysOrgDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-07-21
 */
@RestController
@RequestMapping("/sysOrg")
public class SysOrgController {

    Logger logger= LoggerFactory.getLogger(SysOrgController.class);

    @Autowired
    SysOrgService sysOrgService;

    @GetMapping("/tree")
    public RespDTO getOrgTree() {

        List<SysOrgDTO> tree = sysOrgService.listOrgTree();
        return RespDTO.onSuc(tree);
    }


    @PostMapping("")
    public RespDTO createOrg(@RequestBody SysOrgAddDomain sysOrgAddDomain) {
        SysOrg sysOrg = new SysOrg();
        Long startTime=System.currentTimeMillis();
        BeanUtils.copy(sysOrgAddDomain, sysOrg);
        Long endCopyBeanTime=System.currentTimeMillis();
        logger.info("copy bean takes {} ms",(endCopyBeanTime-startTime));
        if (sysOrgService.save(sysOrg)) {
            logger.info("insert  data takes {} ms",(System.currentTimeMillis()-endCopyBeanTime));
            return RespDTO.onSuc(null);
        } else {
            throw new AriesException(ErrorCode.INSERT_DATA_FAIL);
        }
    }

    @DeleteMapping("")
    public RespDTO deleteOrg(Long id) {

        if (id != null) {
            if (sysOrgService.removeById(id)) {
                return RespDTO.onSuc(null);
            } else {
                throw new AriesException(ErrorCode.DELETE_DATA_FAIL);
            }
        } else {
            throw new AriesException(ErrorCode.DELETE_DATA_FAIL);
        }

    }

}

