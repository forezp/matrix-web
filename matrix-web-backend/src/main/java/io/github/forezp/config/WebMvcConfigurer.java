package io.github.forezp.config;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.github.forezp.aop.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


/**
 * 加载静态资源类
 * liuzhize 2019年3月7日下午3:25:49
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

//    @Bean
//    public HttpMessageConverters fastJsonConfigure(){
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
       // fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //日期格式化
       // fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
//        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
//        serializeConfig.put(Long.class, ToStringSerializer.instance);
//        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
//        fastJsonConfig.setSerializeConfig(serializeConfig);
//        converter.setFastJsonConfig(fastJsonConfig);
//        List<MediaType> supportedMediaTypes=new ArrayList<>();
//        supportedMediaTypes.add(APPLICATION_FORM_URLENCODED);
//        supportedMediaTypes.add(APPLICATION_JSON);
//        supportedMediaTypes.add(APPLICATION_JSON_UTF8);
//        converter.setSupportedMediaTypes(supportedMediaTypes);
//        return new HttpMessageConverters(converter);
//    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }
}