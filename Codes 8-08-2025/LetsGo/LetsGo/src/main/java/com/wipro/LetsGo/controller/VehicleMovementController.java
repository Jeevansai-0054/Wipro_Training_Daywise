package com.wipro.LetsGo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.LetsGo.entity.VehicleMovement;
import com.wipro.LetsGo.repository.VehicleMovementRepository;
import com.wipro.LetsGo.service.VehicleMovementService;

@RestController
public class VehicleMovementController {

    @Autowired
    private VehicleMovementService service;

    @PostMapping("/move")
    public String moveVehicle(@RequestBody VehicleMovement movement) {
        service.saveMovement(movement);
        return "Vehicle movement saved successfully!";
    }
    @GetMapping("/movements")
    public List<VehicleMovement> getAllMovements()
    {
    	return service.getAllMovements();
    }
}
