package io.github.forezp.modules.system.entity;

import io.github.forezp.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 代码标号
     */
    private String codeId;

    /**
     * 代码名称
     */
    private String codeName;

    /**
     * 项目编号
     */
    private String typeId;

    /**
     * 项目名称
     */
    private String typeName;

    /**
     * 排序号
     */
    private String sort;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private Integer status;


}
