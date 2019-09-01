package io.github.forezp.modules.activiti.service.impl;

import com.google.common.collect.Lists;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.UserUtils;
import io.github.forezp.modules.activiti.constant.ActivitiConstants;
import io.github.forezp.modules.activiti.util.ActivitiUtils;
import io.github.forezp.modules.activiti.vo.dto.ActivityDTO;
import io.github.forezp.modules.activiti.vo.dto.HistoricDTO;
import io.github.forezp.modules.activiti.vo.dto.TaskDTO;
import io.github.forezp.modules.activiti.vo.dto.TaskListDTO;
import io.github.forezp.modules.activiti.vo.form.CompleteTask;
import io.github.forezp.modules.activiti.vo.form.StartTask;
import io.github.forezp.modules.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.*;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.el.UelExpressionCondition;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskQuery;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: xufei.
 * @createTime: 2017/8/23.
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class TaichiTaskService {



	@Autowired
	private ProcessEngineFactoryBean processEngineFactory;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	//发送邮件
//    @Autowired
//    private SendMessageClient sendMessageClient;

    /**
     * 待办任务
     * @param title
     * @param category
     * @param page
     * @param pageSize
     * @return
     */
    public TaskListDTO getTodoTasks(String title, String category, int page, int pageSize) {
        String userId = UserUtils.getCurrentUser();
        List<TaskDTO> tasks = new ArrayList<>();
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId)
                .active().includeProcessVariables().orderByTaskCreateTime().desc();
        if (StringUtils.isNotBlank(category)) {
            taskQuery.taskCategory(category);
        }
        if (StringUtils.isNotBlank(title)){
            taskQuery.processVariableValueLikeIgnoreCase("title", "%" + title + "%");
        }
        List<TaskDTO> taskDTOS = taskQuery.listPage((page - 1) * pageSize, pageSize)
                .stream()
                .map(task -> {
                    TaskDTO dto = getTaskDTO(task, task.getAssignee() == null ? ActivitiConstants.TASK_STATUS_CLAIM : ActivitiConstants.TASK_STATUS_TODO);
                    dto.setTime(task.getCreateTime().getTime());
                    return dto;
                })
                .collect(Collectors.toList());
        tasks.addAll(taskDTOS);
        return ActivitiUtils.toTaskListDTO(tasks, category, page, pageSize, taskQuery.count());
    }

    /**
     * 已完成任务
     * @param title
     * @param category
     * @param page
     * @param pageSize
     * @return
     */
    public TaskListDTO getFinishTasks(String title, String category, int page, int pageSize) {
        String userId = UserUtils.getCurrentUser();
        List<TaskDTO> tasks = new ArrayList<>();
        HistoricTaskInstanceQuery histTaskQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId)
                .finished().includeProcessVariables().orderByHistoricTaskInstanceEndTime().desc();
        if (StringUtils.isNotBlank(category)) {
            histTaskQuery.taskCategory(category);
        }
        if (StringUtils.isNotBlank(title)){
            histTaskQuery.processVariableValueLikeIgnoreCase("title", "%" + title + "%");
        }
        Map<String, List<HistoricTaskInstance>> collect = histTaskQuery.list()
                .stream()
                .collect(Collectors.groupingBy(taskInstance -> taskInstance.getProcessInstanceId()));
        collect.entrySet()
                .stream()
                .forEach(entry -> {
                    HistoricTaskInstance task = entry.getValue().get(0);
                    HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
                            .includeProcessVariables().singleResult();
                    TaskDTO dto = getTaskDTO(task, processInstance.getEndTime() == null ? ActivitiConstants.PROCESS_INSTANCE_ING : ActivitiConstants.PROCESS_INSTANCE_DONE);
                    dto.setTime(task.getEndTime().getTime());
                    tasks.add(dto);
                });
        List<TaskDTO> taskDTOs = tasks.stream().sorted(Comparator.comparing(TaskDTO::getTime).reversed()).collect(Collectors.toList());
        return ActivitiUtils.toTaskListDTO(new PageResultsDTO(page, pageSize, taskDTOs), category);
    }

    /**
     * 我发起的任务
     * @param title
     * @param category
     * @param page
     * @param pageSize
     * @return
     */
    public TaskListDTO getTasksByStarter(String title, String category, int page, int pageSize) {
        String userId = UserUtils.getCurrentUser();
        List<TaskDTO> tasks = new ArrayList<>();
        HistoricProcessInstanceQuery histProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery().startedBy(userId)
                .includeProcessVariables().orderByProcessInstanceStartTime().desc();
        if (StringUtils.isNotBlank(category)) {
            histProcessInstanceQuery.processDefinitionCategory(category);
        }
        if (StringUtils.isNotBlank(title)){
            histProcessInstanceQuery.variableValueLikeIgnoreCase("title", "%" + title + "%");
        }
        histProcessInstanceQuery.listPage((page - 1) * pageSize, pageSize)
                .stream()
                .forEach(processInstance -> {
                    HistoricTaskInstanceQuery taskQuery = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).includeProcessVariables()
                    .orderByTaskCreateTime().desc();
                    TaskDTO dto = getTaskDTO(taskQuery.list().get(0), processInstance.getEndTime() == null ? ActivitiConstants.PROCESS_INSTANCE_ING : ActivitiConstants.PROCESS_INSTANCE_DONE);
                    dto.setTime(processInstance.getStartTime().getTime());
                    tasks.add(dto);
                });
        return ActivitiUtils.toTaskListDTO(tasks, category, page, pageSize, histProcessInstanceQuery.count());
    }

    private ProcessDefinition getProcessDefinition(String processDefinitionId) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    }

    private Deployment getDeployment(String deploymentId) {
        return repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
    }

    private TaskDTO getTaskDTO(TaskInfo task, String status) {
        ProcessDefinition processDefinition = getProcessDefinition(task.getProcessDefinitionId());
        String deploymentId = processDefinition.getDeploymentId();
        Deployment deployment = getDeployment(deploymentId);
        return ActivitiUtils.toTaskDTO(task, status, processDefinition, deployment);
    }

    /**
     * 获取流转历史列表
     * @param procInsId 流程实例
     *
     */
    public List<HistoricDTO> historicFlowList(String procInsId) {
        List<HistoricDTO> result = new ArrayList<>();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().includeProcessVariables()
                .processInstanceId(procInsId).orderByTaskCreateTime().asc().list();
        list.stream()
                .forEach(task -> {
                    HistoricDTO dto = new HistoricDTO();
                    dto.setAssignee(task.getAssignee());
                    SysUser sysUser = ActivitiUtils.getUserInfo(task.getAssignee());
                    if (sysUser==null) {
                        throw new AriesException(ErrorCode.USER_IS_NOT_EXIST);
                    }
                    dto.setAssigneeName(sysUser.getRealname());
                    dto.setAvatar(sysUser.getAvatar());
                    if (StringUtils.isNotBlank(task.getId())){
                        List<Comment> commentList = taskService.getTaskComments(task.getId());
                        if (commentList.size()>0){
                            dto.setComment(commentList.get(0).getFullMessage());
                        }
                    }
                    dto.setStartTime(task.getStartTime() == null ? null : task.getStartTime().getTime());
                    dto.setEndTime(task.getEndTime() == null ? null : task.getEndTime().getTime());
                    dto.setDuration(task.getDurationInMillis());
                    dto.setActivityName(task.getName());
                    result.add(dto);
                });
        return result;
    }
//    public List<HistoricDTO> historicFlowList(String procInsId, String startAct, String endAct){
//        List<HistoricDTO> result = new ArrayList<>();
//        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInsId)
//                .orderByHistoricActivityInstanceStartTime().asc().orderByHistoricActivityInstanceEndTime().asc().list();
//        Map<String, Integer> actMap = Maps.newHashMap();
//        for (int i = 0; i < list.size(); i++) {
//            HistoricActivityInstance histIns = list.get(i);
//            if (StrUtil.isNotBlank(startAct) && !startAct.equals(histIns.getActivityId())) {
//                continue;
//            }
//            if (StrUtil.isNotBlank(histIns.getAssignee())
//                    || "startEvent".equals(histIns.getActivityType())
//                    || "endEvent".equals(histIns.getActivityType())) {
//                // 给节点增加一个序号
//                Integer actNum = actMap.get(histIns.getActivityId());
//                Optional.ofNullable(actNum)
//                        .ifPresent(num -> actMap.put(histIns.getActivityId(), actMap.size()));
//                HistoricDTO dto = new HistoricDTO();
//                dto.setStartTime(histIns.getStartTime() == null ? null : histIns.getStartTime().getTime());
//                dto.setEndTime(histIns.getEndTime() == null ? null : histIns.getEndTime().getTime());
//                dto.setDuration(histIns.getDurationInMillis());
//                dto.setActivityName(histIns.getActivityName());
//                // 获取流程发起人名称
//                if ("startEvent".equals(histIns.getActivityType())){
//                    List<HistoricProcessInstance> hisProIns = historyService.createHistoricProcessInstanceQuery()
//                            .processInstanceId(procInsId).orderByProcessInstanceStartTime().asc().list();
//                    if (hisProIns.size() > 0){
//                        if (StrUtil.isNotBlank(hisProIns.get(0).getStartUserId())){
//                            UserInfo userInfo = ActivitiUtils.getUserInfo(hisProIns.get(0).getStartUserId());
//                            if (userInfo != null) {
//                                dto.setAssignee(histIns.getAssignee());
//                                dto.setAssigneeName(userInfo.getSysUser().getRealname());
//                                dto.setAvatar(userInfo.getSysUser().getAvatar());
//                            }
//                        }
//                    }
//                }
//                // 获取任务执行人名称
//                if (StrUtil.isNotEmpty(histIns.getAssignee())){
//                    UserInfo userInfo = ActivitiUtils.getUserInfo(histIns.getAssignee());
//                    if (userInfo != null) {
//                        dto.setAssignee(histIns.getAssignee());
//                        dto.setAssigneeName(userInfo.getSysUser().getRealname());
//                        dto.setAvatar(userInfo.getSysUser().getAvatar());
//                    }
//                }
//                // 获取意见评论内容
//                if (StrUtil.isNotBlank(histIns.getTaskId())){
//                    List<Comment> commentList = taskService.getTaskComments(histIns.getTaskId());
//                    if (commentList.size()>0){
//                        dto.setComment(commentList.get(0).getFullMessage());
//                    }
//                }
//                result.add(dto);
//            }
//            // 过滤结束节点后的节点
//            if (StrUtil.isNotBlank(endAct) && endAct.equals(histIns.getActivityId())){
//                boolean bl = false;
//                Integer actNum = actMap.get(histIns.getActivityId());
//                // 该活动节点，后续节点是否在结束节点之前，在后续节点中是否存在
//                for (int j = i+1; j < list.size(); j++){
//                    HistoricActivityInstance hi = list.get(j);
//                    Integer actNumA = actMap.get(hi.getActivityId());
//                    if ((actNumA != null && actNumA < actNum) || StrUtil.equals(hi.getActivityId(), histIns.getActivityId())){
//                        bl = true;
//                    }
//                }
//                if (!bl){
//                    break;
//                }
//            }
//        }
//        return result;
//    }

    /**
     * 流程跟踪图
     * @param processInstanceId 流程实例ID
     * @return 封装了各种节点信息
     */
    public List<Map<String, Object>> traceProcess(String processInstanceId) {
        Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
        // FIXME: 2017/8/21 测试这两种方式是不是都可以，按理应该都可以
//        String activityId = PropertyUtils.getProperty(execution, "activityId").toString();
        String activityId = execution.getActivityId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
                .singleResult();
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        List<ActivityImpl> activitiList = processDefinition.getActivities();
        List<Map<String, Object>> activityInfos = new ArrayList<>();
        activitiList.stream().forEach(activity -> {
            boolean currentActiviti = activity.getId().equals(activityId);
            Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, processInstance, currentActiviti);
            activityInfos.add(activityImageInfo);
        });
        return activityInfos;
    }

    /**
     * 封装输出信息，包括：当前节点的X、Y坐标、变量信息、任务类型、任务描述
     * @param activity
     * @param processInstance
     * @param currentActiviti
     * @return
     */
    private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity, ProcessInstance processInstance, boolean currentActiviti) {
        Map<String, Object> vars = new HashMap<>();
        Map<String, Object> activityInfo = new HashMap<>();
        activityInfo.put("currentActiviti", currentActiviti);
        setPosition(activity, activityInfo);
        setWidthAndHeight(activity, activityInfo);
        Map<String, Object> properties = activity.getProperties();
        vars.put("任务类型", ActivitiUtils.parseToZhType(properties.get("type").toString()));
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        if (activityBehavior instanceof UserTaskActivityBehavior) {
            // 当前节点的task
            Task currentTask = getCurrentTaskInfo(processInstance);
			// 当前任务的分配角色
            UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
            TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
            Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
            if (!candidateGroupIdExpressions.isEmpty()) {
                // 任务的处理角色
                setTaskGroup(vars, candidateGroupIdExpressions);
                // 当前处理人
                Optional.ofNullable(currentTask)
                        .ifPresent(task -> setCurrentTaskAssignee(vars, task));
            }
        }
        vars.put("节点说明", properties.get("documentation"));
        String description = activity.getProcessDefinition().getDescription();
        vars.put("描述", description);
        activityInfo.put("vars", vars);
        return activityInfo;
    }

    /**
     * 设置宽度、高度属性
     * @param activity
     * @param activityInfo
     */
    private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo) {
        activityInfo.put("width", activity.getWidth());
        activityInfo.put("height", activity.getHeight());
    }

    /**
     * 设置坐标位置
     * @param activity
     * @param activityInfo
     */
    private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo) {
        activityInfo.put("x", activity.getX());
        activityInfo.put("y", activity.getY());
    }

    /**
     * 获取当前节点信息
     * @param processInstance
     * @return
     */
    private Task getCurrentTaskInfo(ProcessInstance processInstance) {
        Task currentTask = null;
        try {
            String activitiId = (String) PropertyUtils.getProperty(processInstance, "activityId");
            currentTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId)
                    .singleResult();
        } catch (Exception e) {
            // FIXME: 2017/8/18 异常
        }
        return currentTask;
    }

    private void setTaskGroup(Map<String, Object> vars, Set<Expression> candidateGroupIdExpressions) {
        String roles = candidateGroupIdExpressions
                .stream()
                .map(expression ->
                        identityService.createGroupQuery()
                                .groupId(expression.getExpressionText())
                                .singleResult()
                                .getName())
                .collect(Collectors.joining(""));
        vars.put("任务所属角色", roles);
    }

    /**
     * 设置当前处理人信息
     *
     * @param vars
     * @param currentTask
     */
    private void setCurrentTaskAssignee(Map<String, Object> vars, Task currentTask) {
        String assignee = currentTask.getAssignee();
        if (assignee != null) {
            User assigneeUser = identityService.createUserQuery().userId(assignee).singleResult();
            String userInfo = assigneeUser.getFirstName() + " " + assigneeUser.getLastName();
            vars.put("当前处理人", userInfo);
        }
    }

    /**
     * 启动流程
     * @param start
     * @return
     */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
    public String startProcess(StartTask start) {
        String userId = UserUtils.getCurrentUser();
        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(userId);
		// 设置流程中的变量：标题
        Map<String, Object> vars = new HashMap<>();
        vars.put("title", start.title);

		// 启动流程
		ProcessInstance procIns = runtimeService.startProcessInstanceByKey(start.procDefKey, start.entityId, vars);
        // 获取流程，设置任务参数
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(start.procDefKey).latestVersion().singleResult();
        Task task = getTaskByPid(procIns.getProcessInstanceId());
        task.setCategory(processDefinition.getCategory());
        taskService.saveTask(task);
        taskService.setAssignee(task.getId(), start.userId);
        sendSysMessage(start.userId, start.title, "");
        // 返回实例id，用于存储到业务表中
		return procIns.getId();
	}

    public Task getTaskByPid(String pid){
		return taskService.createTaskQuery().processInstanceId(pid).singleResult();
	}

	/**
	 * 签收任务
	 * @param taskId 任务ID
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void claim(String taskId){
        String userId = UserUtils.getCurrentUser();
        taskService.claim(taskId, userId);
	}

    /**
     * 提交任务并保存评论
     * @param form
     */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void complete(CompleteTask form){
		// 添加意见
        Map<String, Object> vars = new HashMap();
        if ((form.flag + "").startsWith(ActivitiConstants.TASK_AUDIT_AGREE)) {
            addComment(form.taskId, form.procInsId, "【同意】 ", form.comment, vars);
        } else if ((form.flag + "").startsWith(ActivitiConstants.TASK_AUDIT_DISAGREE)) {
            addComment(form.taskId, form.procInsId, "【不同意】 ", form.comment, vars);
        } else if ((form.flag + "").startsWith(ActivitiConstants.TASK_AUDIT_BACK)) {
            addComment(form.taskId, form.procInsId, "【退回】 ", form.comment, vars);
        } else {
            throw new AriesException(ErrorCode.TASK_AUDIT_PARAM);
        }
        vars.put("pass", form.flag);
        if (StringUtils.isNotBlank(form.comment)) {
            vars.put("comment", form.comment);
        }
        // 提交任务
        taskService.setVariablesLocal(form.taskId, vars);
		taskService.complete(form.taskId, vars);
        Task task = getTaskByPid(form.procInsId);
        Optional.ofNullable(task)
                .ifPresent(t ->{
                    if (StringUtils.isNotBlank(form.userId)) {
                        taskService.setAssignee(task.getId(), form.userId);
                        sendSysMessage(form.userId, (String) taskService.getVariable(task.getId(), "title"), "");
                    }
                });
	}

	private void sendSysMessage(String userId, String title, String content) {
	    List<String> recieverIds = new ArrayList<>();
        recieverIds.add(userId);
       // sendMessageClient.addSysMessage(recieverIds, MessageTypeEnum.NEW_WAIT.getCode(), title, content, null, false);
    }

	private void addComment(String taskId, String processInstanceId, String judgment, String message, Map<String, Object> vars) {
	    String comment;
	    if (StringUtils.isNotBlank(message)) {
	        comment = judgment + message;
        } else {
	        comment = judgment;
        }
        taskService.addComment(taskId, processInstanceId, comment);
	    vars.put(ActivitiConstants.APPROVE_RESULT, judgment);
	    vars.put(ActivitiConstants.APPROVE_SUGGESTION, comment);
    }


    /**
     * 删除任务
     * @param taskId 任务ID
     * @param deleteReason 删除原因
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteTask(String taskId, String deleteReason){
        taskService.deleteTask(taskId, deleteReason);
    }
//
//	/**
//	 * 完成第一个任务
//	 * @param procInsId
//	 */
//	@Transactional(readOnly = false)
//	public void completeFirstTask(String procInsId){
//		completeFirstTask(procInsId, null, null, null);
//	}
//
//	/**
//	 * 完成第一个任务
//	 * @param procInsId
//	 * @param comment
//	 * @param title
//	 * @param vars
//	 */
//	@Transactional(readOnly = false)
//	public void completeFirstTask(String procInsId, String comment, String title, Map<String, Object> vars){
//		String userId = UserUtils.getUser().getLoginName();
//		Task task = taskService.createTaskQuery().taskAssignee(userId).processInstanceId(procInsId).active().singleResult();
//		if (task != null){
//			complete(task.getId(), procInsId, comment, title, vars);
//		}
//	}
//
////	/**
////	 * 委派任务
////	 * @param taskId 任务ID
////	 * @param userId 被委派人
////	 */
////	public void delegateTask(String taskId, String userId){
////		taskService.delegateTask(taskId, userId);
////	}
////
////	/**
////	 * 被委派人完成任务
////	 * @param taskId 任务ID
////	 */
////	public void resolveTask(String taskId){
////		taskService.resolveTask(taskId);
////	}
////
////	/**
////	 * 回退任务
////	 * @param taskId
////	 */
////	public void backTask(String taskId){
////		taskService.
////	}
//
//	/**
//	 * 添加任务意见
//	 */
//	public void addTaskComment(String taskId, String procInsId, String comment){
//		taskService.addComment(taskId, procInsId, comment);
//	}
//
//
//	/**
//	 * 任务后退一步
//	 */
//    @Transactional(readOnly = false)
//	public void taskBack(String procInsId, Map<String, Object> variables) {
//		taskBack(getCurrentTask(procInsId), variables);
//	}
//
//	/**
//	 * 任务后退至指定活动
//	 */
//    @Transactional(readOnly = false)
//	public void taskBack(TaskEntity currentTaskEntity, Map<String, Object> variables) {
//		ActivityImpl activity = (ActivityImpl) ProcessDefUtils
//				.getActivity(processEngine, currentTaskEntity.getProcessDefinitionId(), currentTaskEntity.getTaskDefinitionKey())
//				.getIncomingTransitions().get(0).getSource();
//		jumpTask(currentTaskEntity, activity, variables);
//	}
//
//	/**
//	 * 任务前进一步
//	 */
//    @Transactional(readOnly = false)
//	public void taskForward(String procInsId, Map<String, Object> variables) {
//		taskForward(getCurrentTask(procInsId), variables);
//	}
//
//	/**
//	 * 任务前进至指定活动
//	 */
//    @Transactional(readOnly = false)
//	public void taskForward(TaskEntity currentTaskEntity, Map<String, Object> variables) {
//		ActivityImpl activity = (ActivityImpl) ProcessDefUtils
//				.getActivity(processEngine, currentTaskEntity.getProcessDefinitionId(), currentTaskEntity.getTaskDefinitionKey())
//				.getOutgoingTransitions().get(0).getDestination();
//		jumpTask(currentTaskEntity, activity, variables);
//	}
//
//	/**
//	 * 跳转（包括回退和向前）至指定活动节点
//	 */
//    @Transactional(readOnly = false)
//	public void jumpTask(String procInsId, String targetTaskDefinitionKey, Map<String, Object> variables) {
//		jumpTask(getCurrentTask(procInsId), targetTaskDefinitionKey, variables);
//	}
//
//	/**
//	 * 跳转（包括回退和向前）至指定活动节点
//	 */
//    @Transactional(readOnly = false)
//	public void jumpTask(String procInsId, String currentTaskId, String targetTaskDefinitionKey, Map<String, Object> variables) {
//		jumpTask(getTaskEntity(currentTaskId), targetTaskDefinitionKey, variables);
//	}
//
//	/**
//	 * 跳转（包括回退和向前）至指定活动节点
//	 * @param currentTaskEntity 当前任务节点
//	 * @param targetTaskDefinitionKey 目标任务节点（在模型定义里面的节点名称）
//	 * @throws Exception
//	 */
//    @Transactional(readOnly = false)
//	public void jumpTask(TaskEntity currentTaskEntity, String targetTaskDefinitionKey, Map<String, Object> variables) {
//		ActivityImpl activity = ProcessDefUtils.getActivity(processEngine, currentTaskEntity.getProcessDefinitionId(),
//				targetTaskDefinitionKey);
//		jumpTask(currentTaskEntity, activity, variables);
//	}
//
//	/**
//	 * 跳转（包括回退和向前）至指定活动节点
//	 * @param currentTaskEntity 当前任务节点
//	 * @param targetActivity 目标任务节点（在模型定义里面的节点名称）
//	 * @throws Exception
//	 */
//    @Transactional(readOnly = false)
//	private void jumpTask(TaskEntity currentTaskEntity, ActivityImpl targetActivity, Map<String, Object> variables) {
//		CommandExecutor commandExecutor = ((RuntimeServiceImpl) runtimeService).getCommandExecutor();
//		commandExecutor.execute(new JumpTaskCmd(currentTaskEntity, targetActivity, variables));
//		commandExecutor.execute(new DeleteRunningTaskCmd(currentTaskEntity));
//        Task task = taskService.createTaskQuery().processInstanceId(currentTaskEntity.getProcessInstanceId()).singleResult();
//        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskDefinitionKey("audit1").list();
//        String assignee = list.get(list.size() - 1).getAssignee();
//        taskService.addCandidateUser(task.getId(), assignee);
//    }
//
//	/**
//	 * 后加签
//	 */
//	@SuppressWarnings("unchecked")
//	public ActivityImpl[] insertTasksAfter(String procDefId, String procInsId, String targetTaskDefinitionKey, Map<String, Object> variables, String... assignees) {
//		List<String> assigneeList = new ArrayList<String>();
//		assigneeList.add(Authentication.getAuthenticatedUserId());
//		assigneeList.addAll(CollectionUtils.arrayToList(assignees));
//		String[] newAssignees = assigneeList.toArray(new String[0]);
//		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(procDefId);
//		ActivityImpl prototypeActivity = ProcessDefUtils.getActivity(processEngine, processDefinition.getId(), targetTaskDefinitionKey);
//		return cloneAndMakeChain(processDefinition, procInsId, targetTaskDefinitionKey, prototypeActivity.getOutgoingTransitions().get(0).getDestination().getId(), variables, newAssignees);
//	}
//
//	/**
//	 * 前加签
//	 */
//	public ActivityImpl[] insertTasksBefore(String procDefId, String procInsId, String targetTaskDefinitionKey, Map<String, Object> variables, String... assignees) {
//		ProcessDefinitionEntity procDef = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(procDefId);
//		return cloneAndMakeChain(procDef, procInsId, targetTaskDefinitionKey, targetTaskDefinitionKey, variables, assignees);
//	}
//
//	/**
//	 * 分裂某节点为多实例节点
//	 */
//	public ActivityImpl splitTask(String procDefId, String procInsId, String targetTaskDefinitionKey, Map<String, Object> variables, String... assignee) {
//		return splitTask(procDefId, procInsId, targetTaskDefinitionKey, variables, true, assignee);
//	}
//
//	/**
//	 * 分裂某节点为多实例节点
//	 */
//	@SuppressWarnings("unchecked")
//	public ActivityImpl splitTask(String procDefId, String procInsId, String targetTaskDefinitionKey, Map<String, Object> variables, boolean isSequential, String... assignees) {
//		SimpleRuntimeActivityDefinitionEntity info = new SimpleRuntimeActivityDefinitionEntity();
//		info.setProcessDefinitionId(procDefId);
//		info.setProcessInstanceId(procInsId);
//
//		RuntimeActivityDefinitionEntityIntepreter radei = new RuntimeActivityDefinitionEntityIntepreter(info);
//
//		radei.setPrototypeActivityId(targetTaskDefinitionKey);
//		radei.setAssignees(CollectionUtils.arrayToList(assignees));
//		radei.setSequential(isSequential);
//
//		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(procDefId);
//		ActivityImpl clone = new MultiInstanceActivityCreator().createActivities(processEngine, processDefinition, info)[0];
//
//		TaskEntity currentTaskEntity = this.getCurrentTask(procInsId);
//
//		CommandExecutor commandExecutor = ((RuntimeServiceImpl) runtimeService).getCommandExecutor();
//		commandExecutor.execute(new CreateAndTakeTransitionCmd(currentTaskEntity, clone, variables));
//
////		recordActivitiesCreation(info);
//		return clone;
//	}
//
//	private TaskEntity getCurrentTask(String procInsId) {
//		return (TaskEntity) taskService.createTaskQuery().processInstanceId(procInsId).active().singleResult();
//	}
//
//	private TaskEntity getTaskEntity(String taskId) {
//		Task task = taskService.createTaskQuery().taskId(taskId).includeProcessVariables().singleResult();
//		return (TaskEntity) task;
//	}
//
//	@SuppressWarnings("unchecked")
//	private ActivityImpl[] cloneAndMakeChain(ProcessDefinitionEntity procDef, String procInsId, String prototypeActivityId, String nextActivityId, Map<String, Object> variables, String... assignees) {
//		SimpleRuntimeActivityDefinitionEntity info = new SimpleRuntimeActivityDefinitionEntity();
//		info.setProcessDefinitionId(procDef.getId());
//		info.setProcessInstanceId(procInsId);
//
//		RuntimeActivityDefinitionEntityIntepreter radei = new RuntimeActivityDefinitionEntityIntepreter(info);
//		radei.setPrototypeActivityId(prototypeActivityId);
//		radei.setAssignees(CollectionUtils.arrayToList(assignees));
//		radei.setNextActivityId(nextActivityId);
//
//		ActivityImpl[] activities = new ChainedActivitiesCreator().createActivities(processEngine, procDef, info);
//
//		jumpTask(procInsId, activities[0].getId(), variables);
////		recordActivitiesCreation(info);
//
//		return activities;
//	}

//	private void recordActivitiesCreation(SimpleRuntimeActivityDefinitionEntity info) {
//		info.serializeProperties();
//		_activitiesCreationStore.save(info);
//	}

	////////////////////////////////////////////////////////////////////


//	private void recordActivitiesCreation(SimpleRuntimeActivityDefinitionEntity info) throws Exception {
//		info.serializeProperties();
//		_activitiesCreationStore.save(info);
//	}
//
//	/**
//	 * 分裂某节点为多实例节点
//	 *
//	 * @param targetTaskDefinitionKey
//	 * @param assignee
//	 * @throws IOException
//	 * @throws IllegalAccessException
//	 * @throws IllegalArgumentException
//	 */
//	public ActivityImpl split(String targetTaskDefinitionKey, boolean isSequential, String... assignees) throws Exception {
//		SimpleRuntimeActivityDefinitionEntity info = new SimpleRuntimeActivityDefinitionEntity();
//		info.setProcessDefinitionId(processDefinition.getId());
//		info.setProcessInstanceId(_processInstanceId);
//
//		RuntimeActivityDefinitionEntityIntepreter radei = new RuntimeActivityDefinitionEntityIntepreter(info);
//
//		radei.setPrototypeActivityId(targetTaskDefinitionKey);
//		radei.setAssignees(CollectionUtils.arrayToList(assignees));
//		radei.setSequential(isSequential);
//
//		ActivityImpl clone = new MultiInstanceActivityCreator().createActivities(_processEngine, processDefinition, info)[0];
//
//		TaskEntity currentTaskEntity = getCurrentTask();
//		executeCommand(new CreateAndTakeTransitionCmd(currentTaskEntity.getExecutionId(), clone));
//		executeCommand(new DeleteRunningTaskCmd(currentTaskEntity));
//
//		recordActivitiesCreation(info);
//		return clone;
//	}
//
//	public ActivityImpl split(String targetTaskDefinitionKey, String... assignee) throws Exception {
//		return split(targetTaskDefinitionKey, true, assignee);
//	}

	////////////////////////////////////////////////////////////////////

	/**
	 * 读取带跟踪的图片
	 * @param executionId	环节ID
	 * @return	封装了各种节点信息
	 */
	public InputStream tracePhoto(String processDefinitionId, String executionId) {
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		List<String> activeActivityIds = Lists.newArrayList();
		if (runtimeService.createExecutionQuery().executionId(executionId).count() > 0){
			activeActivityIds = runtimeService.getActiveActivityIds(executionId);
		}
		Context.setProcessEngineConfiguration(processEngineFactory.getProcessEngineConfiguration());
		return processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
				.generateDiagram(bpmnModel, "png", activeActivityIds);
	}

    public List<ActivityDTO> getActivityList(String processInstanceId) {
        //已执行的流程节点
        List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();
        List<ActivityDTO> voList = new ArrayList<>();
        historicActivityInstanceList.stream()
                .filter(historicActivityInstance -> ActivitiConstants.ACTIVITI_TYPE_USER_TASK.equals(historicActivityInstance.getActivityType())
                        || ActivitiConstants.ACTIVITI_TYPE_START_EVENT.equals(historicActivityInstance.getActivityType()))
                .forEach(historicActivityInstance -> {
                    ActivityDTO vo = new ActivityDTO();
                    BeanUtils.copyProperties(historicActivityInstance, vo);
                    if (ActivitiConstants.ACTIVITI_TYPE_START_EVENT.equals(historicActivityInstance.getActivityType())) {
                        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                                .processInstanceId(processInstanceId).singleResult();
                        SysUser sysUser = ActivitiUtils.getUserInfo(historicProcessInstance.getStartUserId());
                        if (sysUser==null) {
                            throw new AriesException(ErrorCode.USER_IS_NOT_EXIST);
                        }
                        vo.setAssigneeName(sysUser.getRealname());
                        vo.setAvatar(sysUser.getAvatar());
                    } else {
                        if (!StringUtils.isEmpty(vo.getAssignee())) {
                            SysUser sysUser = ActivitiUtils.getUserInfo(vo.getAssignee());
                            if (sysUser==null) {
                                throw new AriesException(ErrorCode.USER_IS_NOT_EXIST);
                            }
                            vo.setAssigneeName(sysUser.getRealname());
                            vo.setAvatar(sysUser.getAvatar());
                        } else {
                            vo.setAssigneeName("待定");
                        }
                    }
                    //节点状态
                    if (vo.getEndTime() != null) {
                        vo.setActivityState(ActivitiConstants.STATE_DONE);
                    }
                    else {
                        vo.setActivityState(ActivitiConstants.STATE_DOING);
                    }

                    //获取审批结果和审批意见
                    Map<String, String> approveMap = getApproveMap(historicActivityInstance);
                    if (!approveMap.isEmpty()) {
                        vo.setApproved(approveMap.get(ActivitiConstants.APPROVE_RESULT));
                        vo.setSuggestion(approveMap.get(ActivitiConstants.APPROVE_SUGGESTION));
                    }
                    voList.add(vo);
                });

        //活动的节点ID
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        List<String> activeIds = new ArrayList<>();
        ProcessDefinitionEntity processDefinition;
        if (processInstance != null) {
            activeIds = runtimeService.getActiveActivityIds(processInstanceId);
            processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
        } else {
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();
            processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
        }

        //活动节点对象
        List<ActivityImpl> nextActivities = new ArrayList<>();
        PvmActivity curActivity = null;
        for (String activeId : activeIds) {
            ActivityImpl activityImpl = processDefinition.findActivity(activeId);
            if (activityImpl != null) {
                nextActivities.add(activityImpl);
                curActivity = activityImpl;
            }
        }

        if (processInstance != null) {
            findNextActivity(voList, processInstanceId, curActivity);
        }
        return voList;
    }

    /**
     * 从当前节点往后找所有节点
     *
     * @param voList
     * @param processInstanceId
     * @param curActivity
     */
    public void findNextActivity(List<ActivityDTO> voList, String processInstanceId, PvmActivity curActivity) {
        // 一直走到结束
        List<PvmTransition> nextTrans = curActivity.getOutgoingTransitions();
        for (PvmTransition nextTran : nextTrans) {
            PvmActivity activity = nextTran.getDestination();
            if (ActivitiConstants.ACTIVITI_TYPE_USER_TASK.equals(activity.getProperty("type").toString())) {
                Object flowName = nextTran.getProperty("name");
                Object conditionText = nextTran.getProperty("conditionText");
                if (flowName != null && conditionText != null && ActivitiConstants.CONDITION_TEXT_PASSED.equals(conditionText.toString())) {
                    ActivityDTO vo = new ActivityDTO();
                    vo.setId(activity.getId());
                    vo.setActivityName(activity.getProperty("name").toString());
//                    vo.setAssigneeName(getCandidateUserNames((ActivityImpl) activity, processInstanceId));
                    vo.setAssigneeName("待定");
                    vo.setActivityState(ActivitiConstants.STATE_TODO);
                    voList.add(vo);
                    findNextActivity(voList, processInstanceId, activity);
                } else if (flowName == null ||
                        (conditionText != null
                                && !ActivitiConstants.CONDITION_TEXT_REFUSE.equals(conditionText.toString())
                                && !ActivitiConstants.CONDITION_TEXT_BACK.equals(conditionText.toString()))) {
//                    boolean targetTask = isTargetTask(processInstanceId, nextTran);
//                    if (targetTask) {
                        ActivityDTO vo = new ActivityDTO();
                        vo.setId(activity.getId());
                        vo.setActivityName(activity.getProperty("name").toString());
//                        vo.setAssigneeName(getCandidateUserNames((ActivityImpl) activity, processInstanceId));
                        vo.setAssigneeName("待定");
                        vo.setActivityState(ActivitiConstants.STATE_TODO);
                        voList.add(vo);
                        findNextActivity(voList, processInstanceId, activity);
//                    }
                }
            } else {
                findNextActivity(voList, processInstanceId, activity);
            }

        }
    }

    /**
     * 获取历史审批结果和审批意见
     *
     * @param activityInstance 历史任务节点
     * @return
     */
    public Map<String, String> getApproveMap(HistoricActivityInstance activityInstance) {
        //审批结果和审批意见为Local变量
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(activityInstance.getTaskId())) {
            return map;
        }
        List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(activityInstance.getProcessInstanceId()).taskId(activityInstance.getTaskId()).list();
        for (HistoricVariableInstance variableInstance : variableInstances) {
            if (variableInstance.getVariableName().equals(ActivitiConstants.APPROVE_RESULT)) {
                map.put(ActivitiConstants.APPROVE_RESULT, variableInstance.getValue().toString());
            } else {
                map.put(ActivitiConstants.APPROVE_SUGGESTION, variableInstance.getValue().toString());
            }
        }
        return map;
    }

    /**
     * 节点上的文字是否在可选文字中
     *
     * @param type 同意/拒绝
     * @param text 节点文字
     * @return
     */
    public boolean isInApprovedText(String type, String text) {
        if (ActivitiConstants.APPROVED_PASSED.equals(type)) {
            for (String s : ActivitiConstants.APPROVED_PASSED_TEXT) {
                if (s.equals(text)) {
                    return true;
                }
            }
            return false;
        } else if (ActivitiConstants.APPROVED_REJECT.equals(type)) {
            for (String s : ActivitiConstants.APPROVED_REJECT_TEXT) {
                if (s.equals(text)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * 获取尚未执行的节点的可能执行人姓名，以逗号分隔
     * 需要使用命令模式动态执行表达式 不然报lazy load expression out of activiti异常
     *
     * @param activity          流程定义的用户活动节点
     * @param processInstanceId 流程实例ID
     * @return 可能执行人姓名
     */
    public String getCandidateUserNames(final ActivityImpl activity, final String processInstanceId) {
        String result = ((RuntimeServiceImpl) runtimeService).getCommandExecutor()
                .execute((CommandContext commandContext) -> {
                            String retNames = "";
                            ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().processInstanceId
                                    (processInstanceId).singleResult();
                            TaskDefinition taskDefinition = (TaskDefinition) activity.getProperties().get("taskDefinition");
                            if (taskDefinition == null) {
                                return retNames;
                            }

                            //代理人/审批人
                            String assignee = null;
                            if (taskDefinition.getAssigneeExpression() != null) {
                                try {
                                    assignee = (String) taskDefinition.getAssigneeExpression().getValue(execution);
                                } catch (Exception ex) {
                                    log.error("获取收理人出错：" + ex.getMessage());
                                    assignee = null;
                                }
                                SysUser sysUser = ActivitiUtils.getUserInfo(assignee);
                                if (sysUser==null) {
                                    throw new AriesException(ErrorCode.USER_IS_NOT_EXIST);
                                }
                                retNames = assignee != null ? sysUser.getRealname() : "待定";
                                return retNames;
                            }
                            return retNames;
                        }
                );
        return result;

    }

    /**
     * 判断当前是否是合适的路径
     *
     * @param     表达式
     * @param processInstanceId 实例ID
     * @return true符合条件条件的路径
     */
    private boolean isTargetTask(String processInstanceId, final PvmTransition transition) {

        final ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(processInstanceId)
                .singleResult();
        boolean result = ((RuntimeServiceImpl) runtimeService).getCommandExecutor()
                .execute((CommandContext commandContext) -> {
                            UelExpressionCondition flowCondition = (UelExpressionCondition) transition.getProperty("condition");
                            boolean evelRet = flowCondition.evaluate(transition.getId(), execution);
                            return evelRet;
                        }
                );
        return result;
    }

//    public String getStartUserId(ProcessInstance processInstance) {
//        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition
//                (processInstance.getProcessDefinitionId());
//        String initiator = processDefinition.getProperty(BpmnParse.PROPERTYNAME_INITIATOR_VARIABLE_NAME).toString();
//        String assign = runtimeService.getVariable(processInstance.getProcessInstanceId(), initiator).toString();
//        return assign;
//    }

    /**
     * 历史节点对应的流程定义节点
     *
     * @param activityInstance 历史节点
     * @param activities       流程定义节点
     * @return
     */
    public ActivityImpl getActivity(HistoricActivityInstance activityInstance, List<ActivityImpl> activities) {
        for (ActivityImpl activity : activities) {
            if (activity.getId().equals(activityInstance.getActivityId())) {
                return activity;
            }
        }
        return null;
    }
}