package com.wipro.rider.controller;

import com.wipro.rider.entity.Ride;
import com.wipro.rider.kafka.RideProducer;
import com.wipro.rider.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideProducer rideProducer;

    @PostMapping
    public ResponseEntity<String> bookRide(@RequestBody Ride ride) {
        ride.setStatus("Pending");
        Ride savedRide = rideRepository.save(ride);
        rideProducer.sendRideRequest(savedRide);
        return ResponseEntity.ok("Ride booked with ID: " + savedRide.getId());
    }
}