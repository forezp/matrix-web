package io.github.forezp.modules.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author forezp
 * @since 2018-08-03
 */
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单编号
     */
    private String parentId;
    /**
     * 菜单编号
     */
    private String permissionId;
    /**
     * 权限标志
     */
    private String permission;
    /**
     * 菜单描述
     */
    private String remarks;
    /**
     * 菜单状态
     */
    private Integer status;
    /**
     * 菜单类型
     */
    private Integer type;
    /**
     * 按钮路径
     */
    private String icon;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人id
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private String updateBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        ", id=" + id +
        ", name=" + name +
        ", parentId=" + parentId +
        ", permissionId=" + permissionId +
        ", permission=" + permission +
        ", remarks=" + remarks +
        ", status=" + status +
        ", type=" + type +
        ", icon=" + icon +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
