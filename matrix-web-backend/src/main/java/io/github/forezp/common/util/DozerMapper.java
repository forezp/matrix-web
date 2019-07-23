package io.github.forezp.common.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: felix.
 * @createTime: 2017/11/6.
 */
@Configuration
public class DozerMapper {

    @Bean
    public DozerBeanMapper createMapper() {
        return new DozerBeanMapper();
    }
}