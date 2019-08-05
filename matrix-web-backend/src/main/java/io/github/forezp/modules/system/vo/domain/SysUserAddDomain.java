package io.github.forezp.modules.system.vo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by forezp on 2019/7/31.
 */
@Data
public class SysUserAddDomain {

    @NotNull
    private String userId;

    private String realname;

    private Integer sex;

    private String email;

    @NotNull
    private String mobile;

    @NotNull
    private String orgId;

    private String simpleName;//org
    @NotNull
    private Integer status;

    private Date birthday;

    @NotNull
    private String password;

}
