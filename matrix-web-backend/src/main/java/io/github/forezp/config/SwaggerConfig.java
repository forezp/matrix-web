package io.github.forezp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    private static final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ1c2VybmFtZSIsInVzZXJfbmFtZSI6ImFkbWluIiwibmlja19uYW1lIjoiREFTREExMjEiLCJleHAiOjE1MzM5NTM3MjEsImlhdCI6MTUzMzkxMDUyMSwianRpIjoidXNlcklkIn0.WJBnC_Tk1bAiHaOi80TxCk0jJIHU-wLyjd6S96OqPJU";
//    private List<Parameter> parameter() {
//        List<Parameter> params = new ArrayList<>();
//        params.add(new ParameterBuilder().name("Authorization")
//                .description("Authorization Bearer token")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .defaultValue(TOKEN)
//                .required(false).build());
//        return params;
//    }
//
//    @Bean
//    public Docket sysApi() {
//
////        ParameterBuilder tokenPar = new ParameterBuilder();
////        List<Parameter> pars = new ArrayList<Parameter>();
////        tokenPar.name("Authorization").description("令牌").defaultValue(TOKEN).modelRef(new ModelRef("string")).parameterType("header").required(false).build();
////        pars.add(tokenPar.build());
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("io.github.forezp"))
//                .paths(PathSelectors.any())
//                .build().globalOperationParameters(parameter());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Aries api ")
//                .description("Aries快速开发框架")
//                .termsOfServiceUrl("")
//                .contact("weixin:miles02")
//                .version("1.0")
//                .build();
//    }
//}

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger在线文档配置<br>
 * 项目启动后可通过地址：http://host:ip/swagger-ui.html 查看在线文档
 * @version 2018-07-24
 *
 * @author enilu
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("Token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.forezp"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("web-flash api")
                .description("快速构建web管理平台")
                .termsOfServiceUrl("blog.enilu.cn")
                .contact("blog.enilu.cn")
                .version("1.0")
                .build();
    }
}
