package io.github.forezp.modules.activiti.util;

import com.alibaba.fastjson.JSON;
import io.github.forezp.AriesApplication;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.Application;
import io.github.forezp.modules.activiti.vo.dto.ProcessDTO;
import io.github.forezp.modules.activiti.vo.dto.TaskDTO;
import io.github.forezp.modules.activiti.vo.dto.TaskListDTO;
import io.github.forezp.modules.system.entity.SysUser;
import io.github.forezp.modules.system.service.SysUserService;
import io.github.forezp.modules.system.vo.dto.SysRoleDTO;
import io.github.forezp.modules.system.vo.dto.SysUserDTO;
import org.activiti.engine.HistoryService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ActivitiUtils {

    static SysUserService userService = Application.getBean(SysUserService.class);

    static HistoryService historyService = Application.getBean(HistoryService.class);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    public static SysUser getUserInfo(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
        return userService.getUserById(userId);

    }


    /**
     * 节点对应的中文名称
     *
     * @param type
     * @return
     */
    public static String parseToZhType(String type) {
        Map<String, String> types = new HashMap<String, String>();
        types.put("userTask", "用户任务");
        types.put("serviceTask", "系统任务");
        types.put("startEvent", "开始节点");
        types.put("endEvent", "结束节点");
        types.put("exclusiveGateway", "条件判断节点(系统自动根据条件处理)");
        types.put("inclusiveGateway", "并行处理任务");
        types.put("callActivity", "子流程");
        return types.get(type) == null ? type : types.get(type);
    }

    /**
     * 将dto转为activiti的entity
     *
     * @param role
     * @return
     */
    public static GroupEntity toActivitiGroup(SysRoleDTO role) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(role.getRoleId());
        groupEntity.setName(role.getName());
        groupEntity.setType(role.getType() + "");
        groupEntity.setRevision(1);
        return groupEntity;
    }

    /**
     * 将dto转为activiti的entity
     *
     * @param user
     * @return
     */
    public static UserEntity toActivitiUser(SysUserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getUserId());
        userEntity.setFirstName(user.getRealname());
        userEntity.setLastName(StringUtils.EMPTY);
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setRevision(1);
        return userEntity;
    }

    /**
     * 抽取流程实例需要返回的内容
     *
     * @param processDefinition
     * @param deployment
     * @return
     */
    public static ProcessDTO toProcessDTO(ProcessDefinition processDefinition, Deployment deployment) {
        if (null == processDefinition || null == deployment) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
        ProcessDTO dto = new ProcessDTO();
        dto.category = processDefinition.getCategory();
        dto.processonDefinitionId = processDefinition.getId();
        dto.key = processDefinition.getKey();
        dto.name = deployment.getName();
        dto.revision = processDefinition.getVersion();
        dto.deploymentTime = deployment.getDeploymentTime().getTime();
        dto.xmlName = processDefinition.getResourceName();
        dto.picName = processDefinition.getDiagramResourceName();
        dto.deploymentId = deployment.getId();
        dto.suspend = processDefinition.isSuspended();
        dto.description = processDefinition.getDescription();
        return dto;
    }

    public static TaskDTO toTaskDTO(TaskInfo task, String status, ProcessDefinition processDefinition, Deployment deployment) {
        TaskDTO dto = new TaskDTO();
        dto.setTaskId(task.getId());
        dto.setTaskName(task.getName());
//        dto.setTime(historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartTime().getTime());
        dto.setVariable(task.getTaskLocalVariables());
        dto.setPdName(deployment.getName());
        dto.setVersion("V:" + processDefinition.getVersion());
        dto.setProcessInstanceId(task.getProcessInstanceId());
        dto.setStatus(status);
        dto.setTitle((String) task.getProcessVariables().get("title"));
        dto.setNodeKey(task.getTaskDefinitionKey());
        dto.setProcessDefKey(processDefinition.getKey());
        return dto;
    }

    public static TaskListDTO toTaskListDTO(PageResultsDTO dto, String category) {
        TaskListDTO taskList = new TaskListDTO();
        taskList.setCategory(category);
        taskList.setPage(dto.page);
        taskList.setPageSize(dto.pageSize);
        taskList.setTotalCount((int) dto.totalCount);
        taskList.setTasks(dto.getList());
        return taskList;
    }

    public static TaskListDTO toTaskListDTO(List<TaskDTO> tasks, String category, int page, int pageSize, long totalCount) {
        TaskListDTO taskList = new TaskListDTO();
        taskList.setCategory(category);
        taskList.setPage(page);
        taskList.setPageSize(pageSize);
        taskList.setTotalCount((int) totalCount);
        taskList.setTasks(tasks);
        return taskList;
    }
}