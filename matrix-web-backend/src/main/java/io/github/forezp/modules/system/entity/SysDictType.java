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
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDictType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String typeId;

    private String typeName;

    private String typeDescribe;

    private String remarks;


}
