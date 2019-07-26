package io.github.forezp;


import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@MapperScan("io.github.forezp.modules.system.mapper")
public class AriesApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(AriesApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AriesApplication.class);
    }

}
