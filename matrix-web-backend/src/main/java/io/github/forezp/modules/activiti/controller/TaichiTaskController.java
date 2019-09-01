package io.github.forezp.modules.activiti.controller;


import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.activiti.service.impl.TaichiTaskService;
import io.github.forezp.modules.activiti.service.impl.TaskCrlService;
import io.github.forezp.modules.activiti.vo.form.CompleteTask;
import io.github.forezp.modules.activiti.vo.form.StartTask;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author: xufei.
 * @createTime: 2017/8/23.
 */
@RestController
@RequestMapping(value = "/task")
public class TaichiTaskController {

	@Autowired
	private TaichiTaskService taichiTaskService;

	@Autowired
    private TaskCrlService taskCrlService;

	@ApiOperation(value = "待办列表", notes = "待办列表")
	@GetMapping("/todo")
	public RespDTO todoList(@RequestParam(required = false) String title, @RequestParam(required = false) String category,
                            @RequestParam int page, @RequestParam int pageSize) {
        PageUtils.check(page, pageSize);
        return RespDTO.onSuc(taichiTaskService.getTodoTasks(title, category, page, pageSize));
	}

    @ApiOperation(value = "已完成列表", notes = "已完成列表")
	@GetMapping("/finish")
	public RespDTO finishList(@RequestParam(required = false) String title, @RequestParam(required = false) String category,
                              @RequestParam int page, @RequestParam int pageSize) {
        PageUtils.check(page, pageSize);
		return RespDTO.onSuc(taichiTaskService.getFinishTasks(title, category, page, pageSize));
	}

    @ApiOperation(value = "我发起的列表", notes = "我发起的列表")
    @GetMapping("/starter")
    public RespDTO starterList(@RequestParam(required = false) String title, @RequestParam(required = false) String category,
                               @RequestParam int page, @RequestParam int pageSize) {
        PageUtils.check(page, pageSize);
        return RespDTO.onSuc(taichiTaskService.getTasksByStarter(title, category, page, pageSize));
    }


    @ApiOperation(value = "流程流转历史列表", notes = "流程流转历史列表")
    @GetMapping(value = "/history/{procInsId}")
    public RespDTO historicFlow(@PathVariable("procInsId") String procInsId) {
        return RespDTO.onSuc(taichiTaskService.getActivityList(procInsId));
    }

    @ApiOperation(value = "流程流转历史列表", notes = "流程流转历史列表")
    @GetMapping(value = "/flows/{procInsId}")
    public RespDTO activitiList(@PathVariable("procInsId") String procInsId) {
        return RespDTO.onSuc(taichiTaskService.getActivityList(procInsId));
    }
//    /**
//     * 获取流程列表
//     * @param category 流程分类
//     * 见processController里面获取流程列表
//     */
//
//	/**
//	 * 获取流程表单
//	 * @param taskId	任务ID
//	 * @param taskName	任务名称
//	 * @param taskDefKey 任务环节标识
//	 * @param procInsId 流程实例ID
//	 * @param procDefId 流程定义ID
//	 */
//	@RequestMapping(value = "form")
//	public String form(Act act, HttpServletRequest request, Model model){
//
//		// 获取流程XML上的表单KEY
//		String formKey = actTaskService.getFormKey(act.getProcDefId(), act.getTaskDefKey());
//
//		// 获取流程实例对象
//		if (act.getProcInsId() != null){
//			act.setProcIns(actTaskService.getProcIns(act.getProcInsId()));
//		}
//
//		return "redirect:" + ActUtils.getFormUrl(formKey, act);
//
////		// 传递参数到视图
////		model.addAttribute("act", act);
////		model.addAttribute("formUrl", formUrl);
////		return "modules/act/actTaskForm";
//	}

    @ApiOperation(value = "开始流程", notes = "开始任务")
	@PostMapping("/start")
	public RespDTO start(@RequestBody StartTask form) {
	    if (null == form || StringUtils.isBlank(form.procDefKey) || StringUtils.isBlank(form.title)
                || StringUtils.isBlank(form.entityId) || StringUtils.isBlank(form.userId)) {
	        throw new AriesException(ErrorCode.MISSING_ARGS);
        }
		return RespDTO.onSuc(taichiTaskService.startProcess(form));
	}

    @ApiOperation(value = "签收任务", notes = "签收任务")
	@PutMapping("/claim/{taskId}")
	public RespDTO claim(@PathVariable("taskId") String taskId) {
		taichiTaskService.claim(taskId);
		return RespDTO.onSuc(null);
	}

    @ApiOperation(value = "完成任务", notes = "完成任务")
	@PostMapping(value = "/complete")
	public RespDTO complete(@RequestBody CompleteTask form) {
        if (null == form || StringUtils.isBlank(form.taskId) || StringUtils.isBlank(form.procInsId) || null == form.flag) {
            throw new AriesException(ErrorCode.MISSING_ARGS);
        }
		taichiTaskService.complete(form);
		return RespDTO.onSuc(null);
	}

    @ApiOperation(value = "删除任务", notes = "删除任务")
    @DeleteMapping("/{taskId}")
    public RespDTO deleteTask(@PathVariable("taskId") String taskId, @RequestParam String reason) {
        if (StringUtils.isBlank(reason) || StringUtils.isBlank(taskId)) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
        taichiTaskService.deleteTask(taskId, reason);
        return RespDTO.onSuc(null);
    }

    @ApiOperation(value = "跟踪流程实例图片", notes = "跟踪流程图片，需要流程定义id，执行实例id")
	@GetMapping("/trace/photo/{procDefId}/{execId}")
	public void tracePhoto(@PathVariable("procDefId") String procDefId,
                           @PathVariable("execId") String execId, HttpServletResponse response) {
		InputStream imageStream = taichiTaskService.tracePhoto(procDefId, execId);
        try {
            // 输出资源内容到相应对象
            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {

        }
	}

    @ApiOperation(value = "跟踪流程实例信息", notes = "跟踪流程，需要实例id")
	@GetMapping(value = "/trace/info/{proInsId}")
	public RespDTO traceInfo(@PathVariable("proInsId") String proInsId) {
        if (StringUtils.isBlank(proInsId)){
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
		return RespDTO.onSuc(taichiTaskService.traceProcess(proInsId));
	}

    @ApiOperation(value = "节点跳转", notes = "targetKey为目标节点")
    @PutMapping("/jump/{processInstanceId}")
    public RespDTO jumpTo(@PathVariable("processInstanceId") String processInstanceId, @RequestParam String targetKey
            , @RequestParam(required = false) String comment) {
        taskCrlService.jumpTask(processInstanceId, targetKey, comment);
        return RespDTO.onSuc(null);
    }
}
