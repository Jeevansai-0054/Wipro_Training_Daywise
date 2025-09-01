package com.wipro.consumer.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @KafkaListener(topics = "learn-subject", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
