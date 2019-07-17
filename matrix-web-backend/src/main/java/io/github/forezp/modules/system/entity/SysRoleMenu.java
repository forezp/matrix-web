package io.github.forezp.modules.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author forezp
 * @since 2019-07-17
 */
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String roleId;
    private String menuCode;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", menuCode=" + menuCode +
        ", createBy=" + createBy +
        ", updateBy=" + updateBy +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
