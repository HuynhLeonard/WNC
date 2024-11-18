package com.hw7.publisher.subscriber;


import com.hw7.publisher.config.MessageConfig;
import com.hw7.publisher.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class subscriberB {

    @RabbitListener(queues = MessageConfig.QUEUE_B)
    public void consumeMessageFromQueue(Message message) {
        System.out.println("Subscriber B received from queue : " + message.getMessage());
    }
}
