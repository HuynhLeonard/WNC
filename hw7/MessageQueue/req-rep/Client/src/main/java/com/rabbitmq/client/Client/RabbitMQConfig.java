package com.rabbitmq.client.Client;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitMQConfig {

    @Value("${reply.queue.name}")
    private String replyQueueName;

    @Bean
    public Queue replyQueue() {
        return new Queue(replyQueueName, false);
    }
}
