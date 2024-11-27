package com.client.client.config;

import com.client.client.service.SumService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class AppConfig {
    @Bean
    RmiProxyFactoryBean sumService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/SumService");
        rmiProxyFactory.setServiceInterface(SumService.class);
        return rmiProxyFactory;
    }
}
