package com.rabbitmq.server.Server;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue requestQueue() {
        return new Queue("request.queue", false);
    }
}
