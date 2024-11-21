package com.hw7.subscriber.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String topic;
    private String message;
}
