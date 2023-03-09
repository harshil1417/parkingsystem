package com.harshil.parkingsystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshil.parkingsystem.models.ParkingLot;
import com.harshil.parkingsystem.models.Vehicle;
import com.harshil.parkingsystem.models.VehicleEntry;
import com.harshil.parkingsystem.service.ParkingSystemService;
import com.harshil.parkingsystem.utils.ParkingSystemResponse;

@RequestMapping(path = "parking-system")
@RestController
public class ParkingSystemControllers {

	@Autowired
	ParkingSystemService parkingLotService;

	@GetMapping("/version")
	public String version() {
		return "Parking System : 1.0";
	}

	@PostMapping("/parking-lot")
	public ResponseEntity<ParkingSystemResponse> createParkingLot(@RequestBody ParkingLot parkingLot) {
		this.parkingLotService.saveParkingLot(parkingLot);
		return new ResponseEntity<ParkingSystemResponse>(new ParkingSystemResponse("Parking Lot Created"),
				HttpStatus.CREATED);
	}

	@GetMapping("/parking-lot")
	public ResponseEntity<ParkingLot> getParkingLot() {
		return new ResponseEntity<ParkingLot>(this.parkingLotService.getParkingLot().get(), HttpStatus.OK);
	}

	@PostMapping("/vehicle-entry-flow")
	public ResponseEntity<ParkingSystemResponse> createVehicleEntryFlow(@RequestBody Vehicle vehicle) {
		ParkingSystemResponse psRes = null;
		HttpStatus status = null;
		this.parkingLotService.saveVehicleEntryFlow(vehicle);
		psRes = new ParkingSystemResponse("Vehicle entry created");
		status = HttpStatus.CREATED;
		return new ResponseEntity<ParkingSystemResponse>(psRes, status);
	}

	@PostMapping("/vehicle-exit-flow")
	public ResponseEntity<ParkingSystemResponse> createVehicleExitFlow(@RequestBody Vehicle vehicle) {

		ParkingSystemResponse psRes = null;
		HttpStatus status = null;

		this.parkingLotService.saveVehicleExitFlow(vehicle);
		psRes = new ParkingSystemResponse("Vehicle exit flow created");
		status = HttpStatus.CREATED;

		return new ResponseEntity<ParkingSystemResponse>(psRes, status);
	}

	@GetMapping("/vehicle-entries")
	public ResponseEntity<List<VehicleEntry>> getAllVehicleEntries() {
		Optional<List<VehicleEntry>> optListEntries = this.parkingLotService.getAllVehicleEntries();
		if (optListEntries.isPresent()) {
			return new ResponseEntity<List<VehicleEntry>>(optListEntries.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<VehicleEntry>>(new ArrayList<VehicleEntry>(), HttpStatus.NO_CONTENT);
		}
	}

}
