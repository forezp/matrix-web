package io.github.forezp.modules.system.entity;

import io.github.forezp.common.base.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author forezp
 * @since 2019-07-21
 */
public class SysOrg extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 组织id
     */
    private Long id;
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
    private String order;
    /**
     * level 10 总公司 11中心 12部门 13室 20 分公司 21 分公司部门
     */
    private String level;
    /**
     * 备注
     */
    private String remarks;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "SysOrg{" +
        ", id=" + id +
        ", simpleName=" + simpleName +
        ", fullName=" + fullName +
        ", orgId=" + orgId +
        ", pid=" + pid +
        ", status=" + status +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        ", order=" + order +
        ", level=" + level +
        ", remarks=" + remarks +
        "}";
    }


}
