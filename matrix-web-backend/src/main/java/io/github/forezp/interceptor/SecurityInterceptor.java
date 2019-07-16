package io.github.forezp.interceptor;


import io.github.forezp.common.constant.ApiConstants;
import io.github.forezp.common.util.LogUtils;
import io.github.forezp.modules.system.entity.SysMenu;
import io.github.forezp.permission.auth.RequestHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static io.github.forezp.permission.auth.RequestHolder.REQUEST_ID;
import static io.github.forezp.permission.auth.RequestHolder.REQUEST_SERVLET;
import static io.github.forezp.permission.auth.RequestHolder.START_TIME;


@Component
public class SecurityInterceptor implements HandlerInterceptor {

    LogUtils LOG = new LogUtils(SecurityInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //如果用户是非登录用户，则拒绝用户请求
        RequestHolder.get().putIfAbsent(REQUEST_SERVLET, request);
        RequestHolder.get().putIfAbsent(START_TIME, System.currentTimeMillis());

        String method = request.getMethod();
        if (ApiConstants.HTTP_METHOD_OPTIONS.equals(method)) {
            return true;
        }

        String token = request.getHeader("authorization");
        String requestId = request.getHeader("requestId");
        if (StringUtils.isEmpty(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        RequestHolder.get().putIfAbsent(REQUEST_ID, requestId);
        LOG.info("requst uri:"+request.getRequestURI()+",request token:" + token);
        if (StringUtils.isEmpty(token)) {
            // printResponse(request,response);
        }

        return true;
    }

    private void printResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(401);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        Long startTime= (Long) RequestHolder.get().get(START_TIME);
        LOG.info(request.getRequestURI()+" takes {} ms",System.currentTimeMillis()-startTime);
        RequestHolder.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}