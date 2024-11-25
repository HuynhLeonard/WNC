package com.rabbitmq.server.Server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RpcServer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "#{requestQueue.name}")
    public void receive(@Payload String message, @Headers Map<String, Object> headers) {
        System.out.println("Received request: " + message);
        // Process the request...
        String response = "Processed: " + message;

        // Send response to the reply queue
        String replyTo = (String) headers.get("amqp_replyTo");
        String correlationId = (String) headers.get("amqp_correlationId");
        rabbitTemplate.convertAndSend("", replyTo, response, m -> {
            m.getMessageProperties().setCorrelationId(correlationId);
            return m;
        });
    }
}
