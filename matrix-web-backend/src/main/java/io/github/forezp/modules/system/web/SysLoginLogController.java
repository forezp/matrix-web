package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-07-27
 */
@RestController
@RequestMapping("/sys-login-log")
public class SysLoginLogController {

    @Autowired
    SysLoginLogService sysLoginLogService;

    @GetMapping("/pagelist")
    public RespDTO getLoginLogPagelist(@RequestParam Integer page, @RequestParam Integer pageSize,
                                       @RequestParam(required = false) String userId,
                                       @RequestParam(required = false) String beginTime,
                                       @RequestParam(required = false) String endTime) {

        PageResultsDTO resultsDTO = sysLoginLogService.selectPageSysLog(page, pageSize, userId, beginTime, endTime);
        return RespDTO.onSuc(resultsDTO);

    }
}
