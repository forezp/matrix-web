package io.github.forezp.modules.system.vo.dto;

import io.github.forezp.modules.system.entity.SysOrg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by forezp on 2019/7/21.
 * 使用父类的toString方法 @ToString(callSuper = true)
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SysOrgDTO extends SysOrg {


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
