package io.github.forezp.modules.system.vo.dto;

import io.github.forezp.modules.system.entity.SysOrg;

import java.util.List;

/**
 * Created by forezp on 2019/7/21.
 */
public class  SysOrgDTO extends SysOrg {


    List<SysOrgDTO> children;

    private int depth;

    public List<SysOrgDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SysOrgDTO> children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }



}
