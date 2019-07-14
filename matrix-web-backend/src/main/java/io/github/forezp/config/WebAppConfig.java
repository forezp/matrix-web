package io.github.forezp.config;


import io.github.forezp.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * 定义排除拦截路径
     */
    public static String[] EXCLUDE_PATH_PATTERN = {
            //文件上传和下载
            "/file/**",
            //h5端的api，建议生产中将前端h5和后端h5使用的api分拆成两个服务，
            "/offcialSite","/offcialSite/*",
            //druid监控请求
            "/druid/**",
            //验证码地址
            "/validate/*",
            //用户注册和登陆
            "/user/register", "/user/login",
            //错误资源
            "/error",
            //swagger在线api文档资源
            "/swagger-resources","/v2/api-docs","/swagger-ui.html","/webjars/**"
    };

    /**
     * 注册自定义拦截器，添加拦截路径和排除拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH_PATTERN);

    }
}
