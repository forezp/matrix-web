package io.github.forezp.modules.personnel.entity;

import java.util.Date;
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
 * @since 2019-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PlVacation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String name;

    private String reason;

    private String remarks;

    private String procDefKey;

    private Date applyDate;

    private String processId;

    private String vacationType;

    private Date startTime;

    private Date endTime;

    private String step;


}
