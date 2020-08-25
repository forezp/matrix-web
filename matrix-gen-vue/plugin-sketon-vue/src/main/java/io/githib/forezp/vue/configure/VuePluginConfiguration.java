package io.githib.forezp.vue.configure;

import io.githib.forezp.vue.impl.VueProImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VuePluginConfiguration {

    @Bean
    public VueProImpl vueProImpl() {
        return new VueProImpl();
    }

}
