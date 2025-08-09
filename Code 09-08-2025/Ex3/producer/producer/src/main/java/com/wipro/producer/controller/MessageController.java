package com.wipro.producer.controller;

import com.wipro.producer.model.Subject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private final KafkaTemplate<String, Subject> kafkaTemplate;

    public MessageController(KafkaTemplate<String, Subject> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "learn-subject";

    // POST request (from Postman)
    @PostMapping("/send")
    public String sendSubject(@RequestBody Subject subject) {
        System.out.println("[PRODUCER] Sending subject to topic '" + TOPIC + "': " + subject);
        kafkaTemplate.send(TOPIC, subject);
        return "Subject sent: " + subject;
    }
}
