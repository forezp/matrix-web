package io.github.forezp.modules.activiti.service.impl;


import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.activiti.cmd.JumpTaskCmd;
import io.github.forezp.modules.activiti.util.ProcessDefUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: felix.
 * @createTime: 2017/9/25.
 */
@Service
public class TaskCrlService {

    @Autowired
    private ProcessEngineFactoryBean processEngineFactory;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    HistoryService historyService;

    /**
     * 后退一步
     */
    @Transactional(readOnly = false)
    public void taskBack(String procInsId, Map<String, Object> variables) {
        taskBack(getCurrentTask(procInsId), variables);
    }

    /**
     * 后退一步
     */
    @Transactional(readOnly = false)
    public void taskBack(TaskEntity currentTaskEntity, Map<String, Object> variables) {
        ActivityImpl activity = (ActivityImpl) ProcessDefUtils
                .getActivity(processEngine, currentTaskEntity.getProcessDefinitionId(), currentTaskEntity.getTaskDefinitionKey())
                .getIncomingTransitions().get(0).getSource();
        jumpTask(currentTaskEntity, activity, variables);
    }

    /**
     * 前进一步
     */
    @Transactional(readOnly = false)
    public void taskForward(String procInsId, Map<String, Object> variables) {
        taskForward(getCurrentTask(procInsId), variables);
    }

    /**
     * 前进一步
     */
    @Transactional(readOnly = false)
    public void taskForward(TaskEntity currentTaskEntity, Map<String, Object> variables) {
        ActivityImpl activity = (ActivityImpl) ProcessDefUtils
                .getActivity(processEngine, currentTaskEntity.getProcessDefinitionId(), currentTaskEntity.getTaskDefinitionKey())
                .getOutgoingTransitions().get(0).getDestination();
        jumpTask(currentTaskEntity, activity, variables);
    }

    /**
     * 根据pid跳转到指定节点
     * @param procInsId
     * @param targetTaskDefinitionKey
     * @param comment
     */
    public void jumpTask(String procInsId, String targetTaskDefinitionKey, String comment) {
        Map<String, Object> variables = new HashMap<>();
        TaskEntity currentTask = getCurrentTask(procInsId);
        taskService.addComment(currentTask.getId(), procInsId, "【退回】 " + comment);
//        taskService.addComment(currentTask.getId(), procInsId, "跳转评论");
        jumpTask(currentTask, targetTaskDefinitionKey, variables);
    }

    /**
     * 根据pid和当前taskId跳转到指定的节点
     * @param procInsId
     * @param currentTaskId
     * @param targetTaskDefinitionKey
     * @param variables
     */
    public void jumpTask(String procInsId, String currentTaskId, String targetTaskDefinitionKey, Map<String, Object> variables) {
        jumpTask(getTaskEntity(currentTaskId), targetTaskDefinitionKey, variables);
    }

    /**
     * 跳转至指定活动节点
     * @param currentTaskEntity 当前任务节点
     * @param targetTaskDefinitionKey 目标任务节点（在模型定义里面的节点名称）
     */

    public void jumpTask(TaskEntity currentTaskEntity, String targetTaskDefinitionKey, Map<String, Object> variables) {
        ActivityImpl activity = ProcessDefUtils.getActivity(processEngine, currentTaskEntity.getProcessDefinitionId(),
                targetTaskDefinitionKey);
        jumpTask(currentTaskEntity, activity, variables);
    }

    /**
     * 跳转操作（包括回退和向前）至指定活动节点
     * @param currentTaskEntity 当前任务节点
     * @param targetActivity 目标任务节点（在模型定义里面的节点名称）
     */

    private void jumpTask(TaskEntity currentTaskEntity, ActivityImpl targetActivity, Map<String, Object> variables) {
        String processInstanceId = currentTaskEntity.getProcessInstanceId();
        String userId = getLastAssignee(processInstanceId, targetActivity.getId());

        CommandExecutor commandExecutor = ((RuntimeServiceImpl) runtimeService).getCommandExecutor();
        commandExecutor.execute(new JumpTaskCmd(currentTaskEntity, targetActivity, variables));

        // 为退回到指定节点设置上之前该的处理人
        setCandidateUser(currentTaskEntity.getProcessInstanceId(), userId);
    }

    private String getLastAssignee(String processInstanceId, String targetKey) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).taskDefinitionKey(targetKey).list();
        if (null != list && list.size() > 0) {
            String assignee = list.get(list.size() - 1).getAssignee();
            if (StringUtils.isNotBlank(assignee)) {
                return assignee;
            }
        }
        throw new AriesException(ErrorCode.FAIL, "退回时设置操作人失败");
    }

    private void setCandidateUser(String processInstanceId, String userId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.addCandidateUser(task.getId(), userId);
    }

    private TaskEntity getTaskEntity(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).includeProcessVariables().singleResult();
        return (TaskEntity) task;
    }

    private TaskEntity getCurrentTask(String procInsId) {
        return (TaskEntity) taskService.createTaskQuery().processInstanceId(procInsId).active().singleResult();
    }
}
