package io.github.forezp.modules.task.entity;

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
public class QrtzTriggersInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String triggerName;

    private String groupId;

    private String triggerSimpleName;

    private String remarks;

    private String status;


}
