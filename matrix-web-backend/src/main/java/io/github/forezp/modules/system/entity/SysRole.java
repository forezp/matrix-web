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
 * @since 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 1普通用户2管理员角色3任务分配
     */
    private Integer type;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态0 停用1 启用2 锁定
     */
    private Boolean status;



}
