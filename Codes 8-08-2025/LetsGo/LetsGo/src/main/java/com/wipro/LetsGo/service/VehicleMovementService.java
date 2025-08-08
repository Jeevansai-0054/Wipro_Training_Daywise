package com.wipro.LetsGo.service;

import java.util.List;

import com.wipro.LetsGo.entity.VehicleMovement;

public interface VehicleMovementService {
	 VehicleMovement saveMovement(VehicleMovement movement);
	    List<VehicleMovement> getAllMovements();
}
