package io.github.forezp.modules.activiti.vo.dto;

import lombok.Data;

@Data
public class RunningProcessDTO {

    public String id;
    public String processInstanceId;
    public String processDefinitionId;
    public String activityId;
    public Boolean suspended;
    public String processDefinitionName;
    public String title;
    public String startBy;
    public String activityName;
}
