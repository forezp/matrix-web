package io.github.forezp.modules.activiti.entity;

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
 * @since 2019-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ActModelCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String categoryId;

    private String categoryName;

    private String pCategoryId;


}
