package io.github.forezp.modules.system.entity;

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
 * @since 2019-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String ip;

    private Integer status;

    private String loginName;

    private Date loginTime;

    private String realname;


}
