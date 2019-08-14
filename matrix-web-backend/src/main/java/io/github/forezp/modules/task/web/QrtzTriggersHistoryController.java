package io.github.forezp.modules.task.web;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.task.service.QrtzTriggersHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
@RestController
@RequestMapping("/qrtz-triggers-history")
public class QrtzTriggersHistoryController {

    @Autowired
    QrtzTriggersHistoryService qrtzTriggersHistoryService;

    @GetMapping("/pagelist")
    public RespDTO getTriggerGroupPagelist(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String taskGroupId, @RequestParam(required = false) String taskClassName) {
        PageUtils.check(page, pageSize);
        PageResultsDTO resultsDTO = qrtzTriggersHistoryService.getTriggersPage(page, pageSize, taskGroupId, taskClassName);
        return RespDTO.onSuc(resultsDTO);
    }
}
