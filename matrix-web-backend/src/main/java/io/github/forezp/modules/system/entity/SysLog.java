package io.github.forezp.modules.system.entity;

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
 * @since 2019-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String requestId;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 操作者ip
     */
    private String ip;

    private String request;

    private Integer resonseCode;

    private Long  duration;

    /**
     * 响应内容
     */
    private String response;




}
