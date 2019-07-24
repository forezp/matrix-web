package io.github.forezp.modules.system.entity;

import io.github.forezp.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author forezp
 * @since 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String code;

    private String pcode;

    private String url;

    private String icon;

    private Integer ismenu;

    private Integer isopen;

    private Integer levels;

    private String name;

    private Integer num;

    private String pcodes;

    private Integer status;

    private String tips;




}
