//package io.github.forezp.permission.auth;
//
//
//import io.github.forezp.common.util.HttpUtils;
//import io.github.forezp.common.util.JWTUtils;
//import io.github.forezp.permission.whiteurl.WhiteUrlFinder;
//import io.github.forezp.scrorpio.time.ClockUtil;
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by forezp on 2018/8/6.
// */
//
//@Component
//@WebFilter(urlPatterns = "/*", filterName = "authFilter")
//@Slf4j
//public class AuthFilter implements Filter {
//
//
//    @Autowired
//    WhiteUrlFinder whiteUrlFinder;
//
//    private static final String AUTH = "authorization";
//    private static final String BIG_AUTH = "Authorization";
//    private static final String BEARER = "Bearer ";
//    private static final String ERROR_MSG = "{\"code\":\"1\",\"msg\":\"you have no permission to access\"}";
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String uri=httpServletRequest.getRequestURI();
//        log.info(uri);
//        long starTime= ClockUtil.currentTimeMillis();
//        String token = getToken(httpServletRequest);
//        if (!StringUtils.isEmpty(token)) {
//            token = token.replace(BEARER, "");
//            Claims claims;
//            try {
//                claims = JWTUtils.parseJWT(token);
//
//                if (claims != null) {
//                    AuthHolder.set(claims);
//                    filterChain.doFilter(servletRequest, servletResponse);
//                } else {
//                    writeNoPermission(servletResponse);
//                }
//            } catch (Exception e) {
//                writeNoPermission(servletResponse);
//            } finally {
//                if (AuthHolder.get() != null) {
//                    AuthHolder.remove();
//                }
//            }
//        } else {
//            if (whiteUrlFinder.isWhiteUrl(httpServletRequest.getRequestURI())) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                writeNoPermission(servletResponse);
//            }
//        }
//        log.info(uri+" request takes:"+(ClockUtil.currentTimeMillis()-starTime)+"ms");
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    private void writeNoPermission(ServletResponse servletResponse) {
//        try {
//            servletResponse.getWriter().write(ERROR_MSG);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String getToken(HttpServletRequest httpServletRequest) {
//        String token = HttpUtils.getHeaders(httpServletRequest).get(AUTH);
//        if (StringUtils.isEmpty(token)) {
//            token = HttpUtils.getHeaders(httpServletRequest).get(BIG_AUTH);
//        }
//        return token;
//    }
//}
