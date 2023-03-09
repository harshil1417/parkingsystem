package com.harshil.parkingsystem.service;

import java.util.List;
import java.util.Optional;

import com.harshil.parkingsystem.models.ParkingLot;
import com.harshil.parkingsystem.models.Slot;
import com.harshil.parkingsystem.models.Vehicle;
import com.harshil.parkingsystem.models.VehicleEntry;

public interface ParkingSystemService {

	void saveParkingLot(ParkingLot parkingLot);

	Optional<ParkingLot> getParkingLot();

	Optional<Slot> getSlot(Long id);

	void saveVehicleEntryFlow(Vehicle vehicle);

	void saveVehicleExitFlow(Vehicle vehicle);
	
	Optional<List<VehicleEntry>> getAllVehicleEntries();
}
