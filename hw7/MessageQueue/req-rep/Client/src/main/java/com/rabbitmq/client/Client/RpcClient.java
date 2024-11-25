package com.rabbitmq.client.Client;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RpcClient {

    private final RabbitTemplate rabbitTemplate;
    private final Queue replyQueue;

    @Autowired
    public RpcClient(RabbitTemplate rabbitTemplate, Queue replyQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.replyQueue = replyQueue;
    }

    public String send(String message) {
        String correlationId = UUID.randomUUID().toString();
        return (String) rabbitTemplate.convertSendAndReceive("request.queue", message, m -> {
            m.getMessageProperties().setReplyTo(replyQueue.getName());
            m.getMessageProperties().setCorrelationId(correlationId);
            return m;
        });
    }
}
