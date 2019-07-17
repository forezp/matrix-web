package io.github.forezp.modules.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author forezp
 * @since 2019-07-17
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
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
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间/注册时间
     */
    private Date createTime;
    /**
     * 最后更新人
     */
    private Long updateBy;
    /**
     * 最后更新时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPcodes() {
        return pcodes;
    }

    public void setPcodes(String pcodes) {
        this.pcodes = pcodes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", id=" + id +
        ", code=" + code +
        ", pcode=" + pcode +
        ", url=" + url +
        ", icon=" + icon +
        ", ismenu=" + ismenu +
        ", isopen=" + isopen +
        ", levels=" + levels +
        ", name=" + name +
        ", num=" + num +
        ", pcodes=" + pcodes +
        ", status=" + status +
        ", tips=" + tips +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
