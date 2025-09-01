package com.wipro.producer.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "learn-subject";

    // For GET requests (can be called from browser)
    @GetMapping("/send")
    public String sendMessageGet(@RequestParam String message) {
        System.out.println("[CMD OUTPUT - PRODUCER] Sending message to topic '" + TOPIC + "': " + message);
        kafkaTemplate.send(TOPIC, message);
        return "Message sent via GET: " + message;
    }

    // For POST requests (can be called from Postman)
    @PostMapping("/send")
    public String sendMessagePost(@RequestParam String message) {
        System.out.println("[CMD OUTPUT - PRODUCER] Sending message to topic '" + TOPIC + "': " + message);
        kafkaTemplate.send(TOPIC, message);
        return "Message sent via POST: " + message;
    }
}
