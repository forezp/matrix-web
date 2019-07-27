package io.github.forezp.modules.system.vo.dto;

import io.github.forezp.modules.system.entity.SysLoginLog;
import lombok.Data;

/**
 * Created by forezp on 2019/7/27.
 */
@Data
public class SysLoginLogDTO extends SysLoginLog {

    private String statusName;
}
