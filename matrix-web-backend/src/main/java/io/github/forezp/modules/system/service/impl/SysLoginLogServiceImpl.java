package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.ThreadPoolFactory;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.util.BeanUtils;
import io.github.forezp.modules.system.entity.SysLog;
import io.github.forezp.modules.system.entity.SysLoginLog;
import io.github.forezp.modules.system.mapper.SysLoginLogMapper;
import io.github.forezp.modules.system.service.SysLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.system.vo.dto.SysLoginLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import static io.github.forezp.common.constant.ThreadPoolConstants.LOG_THREADPOOL;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-07-27
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Autowired
    SysLoginLogMapper selectPageSysLog;


    @Autowired
    ThreadPoolFactory threadPoolFactory;



    ThreadPoolExecutor threadPoolExecutor;


    @Override
    public PageResultsDTO selectPageSysLog(int page, int pageSize, String userId, String beginTime, String endTime) {
        Page<SysLoginLog> sysLogPage = new Page<>(page, pageSize);
        IPage<SysLoginLog> sysLogIPage = selectPageSysLog.selectPageSysLog(sysLogPage, userId, beginTime, endTime);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysLogIPage.getTotal());
        result.setTotalPage((int) sysLogIPage.getTotal(), pageSize);
        List<SysLoginLog> records = sysLogIPage.getRecords();
        result.setList(transformSysLoginLogDTO(records));
        return result;
    }

    private List<SysLoginLogDTO> transformSysLoginLogDTO(List<SysLoginLog> records) {
        if (records == null) {
            return null;
        }
        List<SysLoginLogDTO> list = new ArrayList<>();
        for (SysLoginLog sysLoginLog : records) {
            SysLoginLogDTO sysLoginLogDTO = new SysLoginLogDTO();
            BeanUtils.copy(sysLoginLog, sysLoginLogDTO);
            if (sysLoginLogDTO.getStatus() == 1) {
                sysLoginLogDTO.setStatusName("登录成功");
            } else {
                sysLoginLogDTO.setStatusName("登录失败");
            }
            list.add(sysLoginLogDTO);
        }
        return list;

    }

    @Override
    public void saveLoginLog(SysLoginLog sysLoginLog) {

        if (threadPoolExecutor == null) {
            threadPoolExecutor = threadPoolFactory.createThreadPoolExecutor(LOG_THREADPOOL);
        }
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                save(sysLoginLog);
            }
        });

    }
}
