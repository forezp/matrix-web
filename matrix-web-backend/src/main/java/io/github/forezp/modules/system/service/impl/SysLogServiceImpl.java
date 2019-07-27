package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.ThreadPoolFactory;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.util.HttpUtils;
import io.github.forezp.common.util.UserUtils;
import io.github.forezp.modules.system.entity.SysLog;
import io.github.forezp.modules.system.entity.SysUser;
import io.github.forezp.modules.system.mapper.SysLogMapper;
import io.github.forezp.modules.system.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.permission.auth.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import static io.github.forezp.common.constant.ThreadPoolConstants.LOG_THREADPOOL;
import static io.github.forezp.permission.auth.RequestHolder.RESP_CODE;
import static io.github.forezp.permission.auth.RequestHolder.RESP_DTO;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-07-26
 */
@Service
@Slf4j
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {


    @Autowired
    ThreadPoolFactory threadPoolFactory;

    @Autowired
    SysLogMapper sysLogMapper;

    ThreadPoolExecutor threadPoolExecutor;


    @Override
    public SysLog createSysLog(HttpServletRequest request, Long duration, String requestId) {

        String method = request.getMethod();
        Map<String, String> params = HttpUtils.getParams(request);
        String paramsStr = params.toString();

        SysLog sysLog = new SysLog();

        sysLog.setUrl(request.getRequestURI());
        sysLog.setIp(HttpUtils.getIpAddress(request));
        if (RequestHolder.get().get(RESP_CODE) != null) {
            sysLog.setResonseCode((Integer) RequestHolder.get().get(RESP_CODE));
        }
        String response = (String) RequestHolder.get().get(RESP_DTO);
        if (!StringUtils.isEmpty(response)) {
            if (response.length() > 500) {
                response = response.substring(0, 500);
            }
            sysLog.setResponse(response);
        }
        sysLog.setDuration(duration);
        sysLog.setRequest(paramsStr);
        sysLog.setMethod(method);
        sysLog.setRequestId(requestId);
        sysLog.setCreateTime(new Date());
        sysLog.setCreateBy(UserUtils.getCurrentUserWithDefault());
        sysLog.setUpdateBy(UserUtils.getCurrentUserWithDefault());
        sysLog.setUpdateTime(new Date());

        return sysLog;
    }


    @Override
    public void saveLogAsync(SysLog sysLog) {
        if (sysLog == null) {
            return;
        }
        if (threadPoolExecutor == null) {
            threadPoolExecutor = threadPoolFactory.createThreadPoolExecutor(LOG_THREADPOOL);
        }
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                save(sysLog);
            }
        });

    }

    @Override
    public PageResultsDTO selectPageSysLog(int page, int pageSize, String createBy,
                                           String method, String beginTime, String endTime) {
        Page<SysLog> sysLogPage = new Page<>(page, pageSize);
        IPage<SysLog> sysLogIPage = sysLogMapper.selectPageSysLog(sysLogPage, createBy, method, beginTime, endTime);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysLogIPage.getTotal());
        result.setList(sysLogIPage.getRecords());
        result.setTotalPage((int) sysLogIPage.getTotal(),pageSize);
        return result;
    }

    public void deleteSysLogByIds(String [] deleteIds){
        List<String> batchIds= Arrays.asList(deleteIds);
        sysLogMapper.deleteBatchIds(batchIds);
    }
}
