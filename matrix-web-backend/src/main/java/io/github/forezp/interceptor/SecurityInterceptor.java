package io.github.forezp.interceptor;


import io.github.forezp.common.constant.ApiConstants;
import io.github.forezp.common.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //如果用户是非登录用户，则拒绝用户请求
        String method = request.getMethod();
        if(ApiConstants.HTTP_METHOD_OPTIONS.equals(method)){
            return true;
        }

        String token = request.getHeader("authorization");
        logger.info("request token:"+token);

        return true;
    }

    private void printResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(401);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        
    }
}