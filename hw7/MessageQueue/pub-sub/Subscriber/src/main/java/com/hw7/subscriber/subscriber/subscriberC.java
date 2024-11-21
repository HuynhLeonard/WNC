package com.hw7.subscriber.subscriber;

import com.hw7.subscriber.config.MessageConfig;
import com.hw7.subscriber.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class subscriberC {

    @RabbitListener(queues = MessageConfig.QUEUE_C)
    public void consumeMessageFromQueue(Message message) {
        System.out.println("Subscriber C received from queue : " + message.getMessage());
    }
}