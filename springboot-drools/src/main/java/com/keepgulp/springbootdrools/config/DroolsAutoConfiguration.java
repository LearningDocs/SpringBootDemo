package com.keepgulp.springbootdrools.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-08 19:10
 **/

@Configuration
public class DroolsAutoConfiguration {

    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }
}
