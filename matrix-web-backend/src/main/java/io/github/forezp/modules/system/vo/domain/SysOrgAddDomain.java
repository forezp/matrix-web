package io.github.forezp.modules.system.vo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by forezp on 2019/7/22.
 */
@Data
public class SysOrgAddDomain implements Serializable {

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
