package io.github.forezp.modules.system.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.system.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author forezp
 * @since 2019-07-26
 */
public interface SysLogService extends IService<SysLog> {

    SysLog createSysLog(HttpServletRequest request, Long duration, String requestId);

    void saveLogAsync(SysLog sysLog);

    PageResultsDTO selectPageSysLog(int page, int pageSize, String createBy,
                                    String method, String beginTime, String endTime);

     void deleteSysLogByIds(String [] deleteIds);


}
