package io.github.forezp.modules.system.web;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.system.entity.SysLog;
import io.github.forezp.modules.system.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by forezp on 2019/7/27.
 */
@RestController
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @GetMapping("/syslog/pagelist")
    public RespDTO selectPageSysLog(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam(required = false) String createBy,
                                    @RequestParam(required = false) String method,
                                    @RequestParam(required = false) String beginTime,
                                    @RequestParam(required = false) String endTime) {

        PageResultsDTO resultsDTO = sysLogService.selectPageSysLog(page, pageSize, createBy, method, beginTime, endTime);
        return RespDTO.onSuc(resultsDTO);

    }

    @DeleteMapping("/syslog/currentpage")
    public RespDTO deleteCurrentPage(@RequestParam String ids) {
        if (StringUtils.isEmpty(ids)) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
        String[] deleteIds = ids.split("-");
        sysLogService.deleteSysLogByIds(deleteIds);
        return RespDTO.onSuc(null);
    }

}
