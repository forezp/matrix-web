package io.github.forezp.modules.system.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.system.entity.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author forezp
 * @since 2019-07-27
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    PageResultsDTO selectPageSysLog(int page, int pageSize,
                                    String userId, String beginTime, String endTime);


    void saveLoginLog(SysLoginLog sysLoginLog);

}
