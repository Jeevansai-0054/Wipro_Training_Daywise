package com.wipro.consumer.service;

import com.wipro.consumer.model.Subject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "learn-subject", groupId = "subject-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Subject subject) {
        System.out.println("[CONSUMER] Received: " + subject);
    }
}
