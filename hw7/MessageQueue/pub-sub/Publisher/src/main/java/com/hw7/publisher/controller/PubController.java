package com.hw7.publisher.controller;

import com.hw7.publisher.config.MessageConfig;
import com.hw7.publisher.model.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PubController {
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody Message message) {
        template.convertAndSend(MessageConfig.EXCHANGE, message.getTopic(), message);
        return "Success !!";
    }
}
