package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.system.service.SysOrgService;
import io.github.forezp.modules.system.vo.dto.SysOrgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    SysOrgService sysOrgService;

    @GetMapping("/tree")
    public RespDTO getOrgTree() {

        List<SysOrgDTO> tree = sysOrgService.listOrgTree();
        return RespDTO.onSuc(tree);
    }

}

