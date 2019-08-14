package io.github.forezp.modules.task.web;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.task.service.QrtzCronTriggersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/task")
@Slf4j
public class QrtzCronTriggersController {
    @Autowired
    QrtzCronTriggersService qrtzCronTriggersService;

    @PostMapping(value = "")
    public void addTask(
            @RequestParam(value = "taskName") String taskName,
            @RequestParam(value = "taskClassName") String taskClassName,
            @RequestParam(value = "taskGroupId") String taskGroupId,
            @RequestParam(value = "cronExpression") String cronExpression)
            throws Exception {
        qrtzCronTriggersService.addTask(taskName, taskClassName, taskGroupId, cronExpression);
    }

    @PostMapping(value = "/pause")
    public void pausejob(@RequestParam(value = "taskClassName") String taskClassName, @RequestParam(value = "taskGroupId") String taskGroupId) throws Exception {
        qrtzCronTriggersService.pauseTask(taskClassName, taskGroupId);
    }

    @PostMapping(value = "/resume")
    public void resumejob(@RequestParam(value = "taskClassName") String taskClassName, @RequestParam(value = "taskGroupId") String taskGroupId) throws Exception {
        qrtzCronTriggersService.resumeTask(taskClassName, taskGroupId);
    }


    @PutMapping(value = "")
    public void updateTask(@RequestParam String taskName,
                           @RequestParam(value = "taskClassName") String taskClassName,
                           @RequestParam(value = "taskGroupId") String taskGroupId,
                           @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        qrtzCronTriggersService.updateTask(taskName,taskClassName, taskGroupId, cronExpression);
    }

    @DeleteMapping(value = "")
    public void deletejob(@RequestParam(value = "taskClassName") String taskClassName, @RequestParam(value = "taskGroupId") String taskGroupId) throws Exception {
        qrtzCronTriggersService.deletTask(taskClassName, taskGroupId);
    }


    @GetMapping("/pagelist")
    public RespDTO getTriggerGroupPagelist(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String taskGroupId, @RequestParam(required = false) String taskClassName) {
        PageUtils.check(page, pageSize);
        PageResultsDTO resultsDTO = qrtzCronTriggersService.getTriggersPage(page, pageSize, taskGroupId, taskClassName);
        return RespDTO.onSuc(resultsDTO);
    }

}
