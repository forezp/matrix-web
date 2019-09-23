package io.github.forezp.aop;


import io.github.forezp.common.constant.ApiConstants;
import io.github.forezp.common.util.LogUtils;
import io.github.forezp.common.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class SecurityInterceptor implements HandlerInterceptor {

    LogUtils LOG = new LogUtils(SecurityInterceptor.class);

    private static final String ERROR_MSG = "{\"code\":\"1\",\"msg\":\"you have no permission to access\"}";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //如果用户是非登录用户，则拒绝用户请求

        String method = request.getMethod();
        if (ApiConstants.HTTP_METHOD_OPTIONS.equals(method)) {
            return true;
        }
        String token = UserUtils.getCurrentToken();
        LOG.info("requst uri:" + request.getRequestURI() + ",request token:" + token);
        if (StringUtils.isEmpty(token)) {
            writeNoPermission(response);
        }
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }


    private void writeNoPermission(ServletResponse servletResponse) {
        try {
            servletResponse.getWriter().write(ERROR_MSG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}