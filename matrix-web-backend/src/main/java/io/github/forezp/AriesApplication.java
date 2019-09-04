package io.github.forezp;


import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan({"io.github.forezp.modules.system.mapper"
        ,"io.github.forezp.modules.task.mapper","io.github.forezp.modules.activiti.mapper","io.github.forezp.modules.personnel.mapper"})
public class AriesApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(AriesApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AriesApplication.class);
    }

}
