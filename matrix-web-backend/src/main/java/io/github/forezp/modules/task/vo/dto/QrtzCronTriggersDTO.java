package io.github.forezp.modules.task.vo.dto;

import io.github.forezp.modules.task.entity.QrtzCronTriggers;
import lombok.Data;

@Data
public class QrtzCronTriggersDTO extends QrtzCronTriggers {
    /**
     * NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED
     */
    private Boolean isPaused;

    private String  nextFireTimeStr;

    private String prevFireTimeStr;

}
