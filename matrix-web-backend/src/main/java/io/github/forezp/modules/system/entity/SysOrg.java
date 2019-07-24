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
public class SysOrg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String simpleName;

    /**
     * 组织名称
     */
    private String fullName;

    /**
     * 菜单编号 (父id+自己的设置id)
     */
    private String orgId;

    /**
     * 父id
     */
    private String pid;

    /**
     * 组织状态1表示正常使用，0表示停用
     */
    private Integer status;



    /**
     * 预留字段（排序使用）
     */
    private Integer sortNo;

    /**
     * level 10 总公司 11中心 12部门 13室 20 分公司 21 分公司部门
     */
    private String level;

    /**
     * 备注
     */
    private String remarks;


}
