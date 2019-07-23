package io.github.forezp.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import io.github.forezp.common.base.BaseEntity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author forezp
 * @since 2019-07-17
 */
public class SysMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
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
