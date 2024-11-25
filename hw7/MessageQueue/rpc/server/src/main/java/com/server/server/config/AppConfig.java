package com.server.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.server.server.service.SumService;
import com.server.server.service.SumServiceImp;

@Configuration
public class AppConfig {
    @Bean
    SumService sumService() {
        return new SumServiceImp();
    }

    @Bean RmiServiceExporter exporter(SumService sumService) {

        // Expose a service via RMI. Remote obect URL is:
        // rmi://<HOST>:<PORT>/<SERVICE_NAME>
        // 1099 is the default port

        Class<SumService> serviceInterface = SumService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(sumService);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }
}
