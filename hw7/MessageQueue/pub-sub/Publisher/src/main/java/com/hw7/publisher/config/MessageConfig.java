package com.hw7.publisher.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    public static final String QUEUE_A = "GROUP_A_QUEUE";
    public static final String QUEUE_B = "GROUP_B_QUEUE";
    public static final String QUEUE_C = "GROUP_C_QUEUE";
    public static final String EXCHANGE = "GROUP_1_DR";
    public static final String ROUTING_KEY_A = "A";
    public static final String ROUTING_KEY_B = "B";
    public static final String ROUTING_KEY_C = "C";

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B);
    }

    @Bean
    public Queue queueC() {
        return new Queue(QUEUE_C);
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingA(Queue queueA, DirectExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_KEY_A);
    }

    @Bean
    public Binding bindingB(Queue queueB, DirectExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_KEY_B);
    }

    @Bean
    public Binding bindingC(Queue queueC, DirectExchange exchange) {
        return BindingBuilder.bind(queueC).to(exchange).with(ROUTING_KEY_B);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}