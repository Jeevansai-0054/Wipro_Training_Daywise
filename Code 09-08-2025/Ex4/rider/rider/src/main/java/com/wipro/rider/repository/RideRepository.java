package com.wipro.rider.repository;

import com.wipro.rider.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface lets Spring Boot handle database operations for Ride
public interface RideRepository extends JpaRepository<Ride, Long> {
}