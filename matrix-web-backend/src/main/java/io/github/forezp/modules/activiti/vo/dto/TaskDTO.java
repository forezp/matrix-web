package io.github.forezp.modules.activiti.vo.dto;

import java.util.Map;

/**
 * @author: xufei.
 * @createTime: 2017/8/17.
 */
public class TaskDTO {

    private String applicant;
    private String taskId;
    private String taskName;
    private String title;
    private String pdName;
    private String version;
    private Long time;
    private String processInstanceId;
    private String status;
    private Variable variable;
    private String nodeKey;
    private String processDefKey;

    public String getProcessDefKey() {
        return processDefKey;
    }

    public void setProcessDefKey(String processDefKey) {
        this.processDefKey = processDefKey;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Map<String, Object> variable) {
        this.variable = new Variable(variable);
    }
}
