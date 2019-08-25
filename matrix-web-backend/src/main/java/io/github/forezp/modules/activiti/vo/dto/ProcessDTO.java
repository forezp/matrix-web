package io.github.forezp.modules.activiti.vo.dto;

import lombok.Data;

@Data
public class ProcessDTO {

    public String category;
    public String processonDefinitionId;
    public String key;
    public String name;
    public Integer revision;
    public Long deploymentTime;
    public String xmlName;
    public String picName;
    public String deploymentId;
    public Boolean suspend;
    public String suspendStr;
    public String description;

}
