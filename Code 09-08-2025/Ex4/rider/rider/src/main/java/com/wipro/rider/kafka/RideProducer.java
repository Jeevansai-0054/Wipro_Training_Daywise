package com.wipro.rider.kafka;

import com.google.gson.Gson;
import com.wipro.rider.entity.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RideProducer {

    private static final String TOPIC = "R2U";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendRideRequest(Ride ride) {
        String message = new Gson().toJson(ride);
        kafkaTemplate.send(TOPIC, message);
    }
}