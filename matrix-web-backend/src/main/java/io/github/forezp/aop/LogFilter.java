package io.github.forezp.aop;


import io.github.forezp.common.constant.ApiConstants;
import io.github.forezp.common.util.HttpUtils;
import io.github.forezp.common.util.JWTUtils;
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
import java.util.UUID;

import static io.github.forezp.permission.auth.RequestHolder.REQUEST_ID;

/**
 * Created by forezp on 2018/8/6.
 */

@Component
@WebFilter(urlPatterns = "/*", filterName = "authFilter")
@Slf4j
public class LogFilter implements Filter {


    @Autowired
    WhiteUrlFinder whiteUrlFinder;

    private static final String AUTH = "authorization";
    private static final String BIG_AUTH = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String ERROR_MSG = "{\"code\":\"1\",\"msg\":\"you have no permission to access\"}";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        httpServletRequest.getMethod();
        String method = httpServletRequest.getMethod();
        if (ApiConstants.HTTP_METHOD_OPTIONS.equals(method)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String requestId = httpServletRequest.getHeader("requestId");
            if (StringUtils.isEmpty(requestId)) {
                requestId = UUID.randomUUID().toString();
            }
            MDC.put(REQUEST_ID,requestId);
            String uri = httpServletRequest.getRequestURI();
            log.info("requst start ,"+method+" "+uri);
            long starTime = ClockUtil.currentTimeMillis();
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("requst end ,"+uri + " request takes:" + (ClockUtil.currentTimeMillis() - starTime) + "ms");
            MDC.clear();
        }

    }

    @Override
    public void destroy() {

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
