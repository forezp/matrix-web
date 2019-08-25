package io.github.forezp.common.util;


import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.activiti.vo.dto.ProcessDTO;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xufei.
 * @createTime: 2017/8/11.
 */
public class ActivitiUtils {


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
        dto.suspendStr = processDefinition.isSuspended() == true ? "未激活" : "激活";
        dto.description = processDefinition.getDescription();
        return dto;
    }
}