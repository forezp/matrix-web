package io.github.forezp.modules.system.vo.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by forezp on 2019/8/1.
 */
@Data
public class SysUserRoleDTO {

    private List<SysRoleDTO> roleDTOList;

    private List<Long> selectIds;
}
