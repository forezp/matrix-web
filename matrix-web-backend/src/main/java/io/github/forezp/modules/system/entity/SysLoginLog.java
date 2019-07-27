package io.github.forezp.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.github.forezp.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author forezp
 * @since 2019-07-27
 */
@Data
@Accessors(chain = true)
public class SysLoginLog {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    protected Long id;

    private String ip;

    private Integer status;

    private String loginName;

    private Date loginTime;

    private String realname;


}
