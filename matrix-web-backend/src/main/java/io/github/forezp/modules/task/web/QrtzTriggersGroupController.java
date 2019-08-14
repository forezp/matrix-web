package io.github.forezp.modules.task.web;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.task.service.QrtzTriggersGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
@RestController
@RequestMapping("/task-group")
public class QrtzTriggersGroupController {


    @Autowired
    QrtzTriggersGroupService qrtzTriggersGroupService;

    @PostMapping("")
    public RespDTO addtriggerGroup(@RequestParam String groupId, @RequestParam String groupName) {
        if (qrtzTriggersGroupService.addTriggerGroup(groupId, groupName)) {
            return RespDTO.onSuc(null);
        } else {
            throw new AriesException(ErrorCode.INSERT_DATA_FAIL);
        }
    }

    @PutMapping("")
    public RespDTO updateTriggerGroup(@RequestParam Long id, @RequestParam String groupName) {
        if (qrtzTriggersGroupService.updateTriigerGroup(id, groupName)) {
            return RespDTO.onSuc(null);
        } else {
            throw new AriesException(ErrorCode.UPDATE_DATA_FAIL);
        }
    }

    @GetMapping("/pagelist")
    public RespDTO getTriggerGroupPagelist(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String groupId, @RequestParam(required = false) String groupName) {
        PageUtils.check(page, pageSize);
        PageResultsDTO resultsDTO = qrtzTriggersGroupService.getTriggerGroupPage(page, pageSize, groupId, groupName);
        return RespDTO.onSuc(resultsDTO);
    }
}
