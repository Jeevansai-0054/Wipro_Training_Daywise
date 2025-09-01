package com.wipro.LetsGo.service;

import com.wipro.LetsGo.entity.VehicleMovement;
import com.wipro.LetsGo.repository.VehicleMovementRepository;
import com.wipro.LetsGo.service.VehicleMovementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleMovementServiceImpl implements VehicleMovementService {

    @Autowired
    private VehicleMovementRepository repository;

    @Override
    public VehicleMovement saveMovement(VehicleMovement movement) {
        return repository.save(movement);
    }

    @Override
    public List<VehicleMovement> getAllMovements() {
        return repository.findAll();
    }
}
