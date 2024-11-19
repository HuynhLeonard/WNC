package com.hw7.subscriber.subscriber;


import com.hw7.subscriber.config.MessageConfig;
import com.hw7.subscriber.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class subscriberA {

    @RabbitListener(queues = MessageConfig.QUEUE_A)
    public void consumeMessageFromQueue(Message message) {
        System.out.println("Subscriber A received from queue : " + message.getMessage());
    }
}
