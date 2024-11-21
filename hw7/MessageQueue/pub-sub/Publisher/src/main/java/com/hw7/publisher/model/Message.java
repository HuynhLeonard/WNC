package com.hw7.publisher.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String topic;
    private String message;
}
