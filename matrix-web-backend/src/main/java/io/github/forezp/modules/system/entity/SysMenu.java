package io.github.forezp.modules.system.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author forezp
 * @since 2019-07-16
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
    private Long modifyBy;
    /**
     * 最后更新时间
     */
    private Date modifyTime;
    private String code;
    private String icon;
    private Integer ismenu;
    private Integer isopen;
    private Integer levels;
    private String name;
    private Integer num;
    private String pcode;
    private String pcodes;
    private Integer status;
    private String tips;
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", id=" + id +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", modifyBy=" + modifyBy +
        ", modifyTime=" + modifyTime +
        ", code=" + code +
        ", icon=" + icon +
        ", ismenu=" + ismenu +
        ", isopen=" + isopen +
        ", levels=" + levels +
        ", name=" + name +
        ", num=" + num +
        ", pcode=" + pcode +
        ", pcodes=" + pcodes +
        ", status=" + status +
        ", tips=" + tips +
        ", url=" + url +
        "}";
    }
}
