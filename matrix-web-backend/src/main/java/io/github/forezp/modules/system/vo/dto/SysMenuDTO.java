package io.github.forezp.modules.system.vo.dto;

import io.github.forezp.modules.system.entity.SysMenu;

import java.util.List;

/**
 * Created by forezp on 2019/7/20.
 */
public class SysMenuDTO extends SysMenu {

    private List<SysMenuDTO> children;

    public List<SysMenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuDTO> children) {
        this.children = children;
    }
}
