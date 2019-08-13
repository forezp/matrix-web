package io.github.forezp.modules.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.github.forezp.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class QrtzCronTriggers extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String cronExpression;

    @TableField(exist = false)
    private String trifggerGroupName;
    private String timeZoneId;
    @TableField(exist = false)
    private Long nextFireTime;
    @TableField(exist = false)
    private Long prevFireTime;
    @TableField(exist = false)
    private String triggerState;
    @TableField(exist = false)
    private String triggerSimpleName;
    @TableField(exist = false)
    private String status;


}
