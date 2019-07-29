package io.github.forezp.modules.system.vo.dto;

import io.github.forezp.modules.system.entity.SysUser;
import lombok.Data;

/**
 * Created by forezp on 2019/7/29.
 */
@Data
public class SysUserDTO extends SysUser {

    private String roleName;
    private String sexName;
    private String orgName;
}
