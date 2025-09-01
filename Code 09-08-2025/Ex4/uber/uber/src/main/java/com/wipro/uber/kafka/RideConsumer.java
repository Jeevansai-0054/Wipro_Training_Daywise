package com.wipro.uber.kafka;

import com.google.gson.Gson;
import com.wipro.uber.entity.Ride;
import com.wipro.uber.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RideConsumer {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideProducer rideProducer;

    @KafkaListener(topics = "R2U", groupId = "uber-group")
    public void consumeRideRequest(String message) {
        Ride ride = new Gson().fromJson(message, Ride.class);
        ride.setStatus("Confirmed");
        rideRepository.save(ride);
        rideProducer.sendStatusUpdate(ride.getId(), "Confirmed");
    }
}