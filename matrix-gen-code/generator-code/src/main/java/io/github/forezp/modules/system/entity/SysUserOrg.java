package io.github.forezp.modules.system.entity;

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
 * @since 2019-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUserOrg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 组织编号
     */
    private String orgId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private Integer status;


}
