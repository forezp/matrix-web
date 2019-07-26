package io.github.forezp.aop;


import io.github.forezp.common.constant.ApiConstants;
import io.github.forezp.common.util.HttpUtils;
import io.github.forezp.common.util.LogUtils;
import io.github.forezp.common.util.UserUtils;
import io.github.forezp.permission.auth.RequestHolder;
import io.github.forezp.permission.whiteurl.WhiteUrlFinder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static io.github.forezp.common.constant.CommonConstants.AURHORIZATION;
import static io.github.forezp.common.constant.CommonConstants.UPPER_AURHORIZATION;
import static io.github.forezp.permission.auth.RequestHolder.REQUEST_ID;
import static io.github.forezp.permission.auth.RequestHolder.REQUEST_SERVLET;
import static io.github.forezp.permission.auth.RequestHolder.START_TIME;


@Component
public class SecurityInterceptor implements HandlerInterceptor {

    LogUtils LOG = new LogUtils(SecurityInterceptor.class);
    @Autowired
    WhiteUrlFinder whiteUrlFinder;

    private static final String AUTH = "authorization";
    private static final String BIG_AUTH = "Authorization";
    private static final String BEARER = "Bearer ";
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
            // printResponse(request,response);
        }

        return true;
    }

    private void printResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(401);
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

    private String getToken(HttpServletRequest httpServletRequest) {
        String token = HttpUtils.getHeaders(httpServletRequest).get(AUTH);
        if (StringUtils.isEmpty(token)) {
            token = HttpUtils.getHeaders(httpServletRequest).get(BIG_AUTH);
        }
        return token;
    }
}