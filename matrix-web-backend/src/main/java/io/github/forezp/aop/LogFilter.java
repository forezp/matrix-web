package io.github.forezp.aop;


import io.github.forezp.common.constant.ApiConstants;
import io.github.forezp.common.util.HttpUtils;
import io.github.forezp.common.util.JWTUtils;
import io.github.forezp.modules.system.entity.SysLog;
import io.github.forezp.modules.system.service.SysLogService;
import io.github.forezp.permission.auth.RequestHolder;
import io.github.forezp.permission.whiteurl.WhiteUrlFinder;
import io.github.forezp.scrorpio.time.ClockUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static io.github.forezp.permission.auth.RequestHolder.*;

/**
 * Created by forezp on 2018/8/6.
 */

@Component
@WebFilter(urlPatterns = "/*", filterName = "logFilter")
@Slf4j
public class LogFilter implements Filter {

    @Autowired
    SysLogService sysLogService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        if (ApiConstants.HTTP_METHOD_OPTIONS.equals(method)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String requestId = httpServletRequest.getHeader("requestId");
            if (StringUtils.isEmpty(requestId)) {
                requestId = UUID.randomUUID().toString();
            }

            MDC.put(REQUEST_ID, requestId);
            RequestHolder.get().putIfAbsent(REQUEST_SERVLET, httpServletRequest);
            RequestHolder.get().putIfAbsent(START_TIME, System.currentTimeMillis());

            String uri = httpServletRequest.getRequestURI();
            log.info("requst start ," + method + " " + uri);
            long starTime = ClockUtil.currentTimeMillis();
            filterChain.doFilter(servletRequest, servletResponse);
            Long duration = ClockUtil.currentTimeMillis() - starTime;
            log.info("requst end ," + uri + " request takes:" + duration + "ms");
            saveSysLog(createSysLog(httpServletRequest, duration, requestId));
            MDC.clear();
            RequestHolder.remove();
        }

    }

    @Override
    public void destroy() {

    }

    private void saveSysLog(SysLog sysLog) {
        sysLogService.save(sysLog);
    }

    private SysLog createSysLog(HttpServletRequest request, Long duration, String requestId) {

        String method = request.getMethod();
        Map<String, String> params = HttpUtils.getParams(request);
        String paramsStr = params.toString();

        SysLog sysLog = new SysLog();
        // sysLog.setCreateBy(UserUtils.getCurrentPrinciple());
        sysLog.setUrl(request.getRequestURI());
        sysLog.setIp(HttpUtils.getIpAddress(request));
        if (RequestHolder.get().get(RESP_CODE) != null) {
            sysLog.setResonseCode((Integer) RequestHolder.get().get(RESP_CODE));
        }
        if (RequestHolder.get().get(RESP_DTO) != null) {
            sysLog.setResponse((String) RequestHolder.get().get(RESP_DTO));
        }
//        sysLog.setCreateTime(new Date());
        sysLog.setDuration(duration);


        sysLog.setRequest(paramsStr);
        sysLog.setMethod(method);
        sysLog.setRequestId(requestId);

        return sysLog;
    }
}
