package com.harshil.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.harshil.parkingsystem.models.Vehicle;
import com.harshil.parkingsystem.models.VehicleEntry;
import com.harshil.parkingsystem.models.VehicleType;
import com.harshil.parkingsystem.service.ParkingSystemServiceImpl;
import com.harshil.parkingsystem.utils.ParkingSystemException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)

public class ParkingsystemApplicationTests {

	@Autowired
	private ParkingSystemServiceImpl parkingSystemServiceImpl;

	@Test
	public void test_vehicle_entry() {
		Vehicle vehicle = new Vehicle();
		vehicle.setNumber("1234");
		vehicle.setType(VehicleType.CAR);
		parkingSystemServiceImpl.saveVehicleEntryFlow(vehicle);

	}
	
	@Test(expected = ParkingSystemException.class)
	public void test_same_vehicle_entry() {
		Vehicle vehicle = new Vehicle();
		vehicle.setNumber("1234");
		vehicle.setType(VehicleType.CAR);
		parkingSystemServiceImpl.saveVehicleEntryFlow(vehicle);
	}
	

	@Test
	public void test_vehicle_exit() {
		Vehicle vehicle = new Vehicle();
		vehicle.setNumber("1234");
		vehicle.setType(VehicleType.CAR);
		vehicle.setId(1L);
		parkingSystemServiceImpl.saveVehicleExitFlow(vehicle);

		Optional<List<VehicleEntry>> parkedVehicles = parkingSystemServiceImpl.getAllVehicleEntries();
		assertEquals(1, parkedVehicles.get().size());

	}

}
