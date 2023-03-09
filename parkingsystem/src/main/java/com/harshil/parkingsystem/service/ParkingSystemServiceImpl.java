package com.harshil.parkingsystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harshil.parkingsystem.dao.ParkingLotDao;
import com.harshil.parkingsystem.dao.SlotDao;
import com.harshil.parkingsystem.dao.VehicleDao;
import com.harshil.parkingsystem.dao.VehicleEntryDao;
import com.harshil.parkingsystem.dao.VehicleInfoDao;
import com.harshil.parkingsystem.models.ParkingLot;
import com.harshil.parkingsystem.models.Slot;
import com.harshil.parkingsystem.models.Vehicle;
import com.harshil.parkingsystem.models.VehicleEntry;
import com.harshil.parkingsystem.models.VehicleInfo;
import com.harshil.parkingsystem.utils.ParkingSystemException;

@Service
@Transactional
public class ParkingSystemServiceImpl implements ParkingSystemService {

	ConcurrentHashMap<Long, Slot> parkingSlotMap = new ConcurrentHashMap<Long, Slot>();
	TreeSet<Long> slotIdSet = new TreeSet<>();
	private final Integer MAX_SLOTS = 5;

	ParkingLotDao parkingLotDao;

	SlotDao slotDao;

	VehicleDao vehicleDao;

	VehicleInfoDao vehicleInfoDao;

	VehicleEntryDao vehicleEntryDao;
	
	
	public ParkingSystemServiceImpl(ParkingLotDao parkingLotDao, SlotDao slotDao, VehicleDao vehicleDao,
			VehicleInfoDao vehicleInfoDao, VehicleEntryDao vehicleEntryDao) {
		super();
		this.parkingLotDao = parkingLotDao;
		this.slotDao = slotDao;
		this.vehicleDao = vehicleDao;
		this.vehicleInfoDao = vehicleInfoDao;
		this.vehicleEntryDao = vehicleEntryDao;
	}

	@PostConstruct
	void setup() {
		List<Long> ids = new ArrayList<>();
		for (long i = 1; i <= MAX_SLOTS; i++) {
			ids.add(i);
		}
		ids.parallelStream().forEach(slot -> {
			slotIdSet.add(slot);
		});
	}

	@Override
	public void saveParkingLot(ParkingLot parkingLot) {
		setup();
		this.parkingLotDao.save(parkingLot);
	}

	@Override
	public Optional<ParkingLot> getParkingLot() {
		return Optional.ofNullable(this.parkingLotDao.findAll().get(0));
	}

	@Override
	public Optional<Slot> getSlot(Long id) {
		return this.slotDao.findById(id);
	}


	@Override
	public void saveVehicleEntryFlow(Vehicle vehicle) {
		if (vehicle.getNumber().isEmpty()) {
			throw new ParkingSystemException("Vehicle number can not be empty !");
		}
		if (vehicle.getType() == null) {
			throw new ParkingSystemException("Vehicle type can not be null !");
		}

		Optional<Vehicle> optVehicle = this.vehicleDao.findByNumber(vehicle.getNumber());
		if(optVehicle.isPresent()) {
			throw new ParkingSystemException("Vehicle already parked !");
		}
		
		Long id = getAvailableSlot(vehicle).get();
		if (null != id) {
			Slot s = new Slot();

			VehicleInfo vehicleInfo = new VehicleInfo();
			vehicleInfo.setInTime(new Date(System.currentTimeMillis()));
			vehicle.setInfo(vehicleInfo);
			saveVehicle(vehicle);

			// Update slot entry
			s.setId(id);
			s.setIsFree(false);
			s.setVehicle(vehicle);
			this.slotDao.save(s);
		}
	}

	@Override
	public void saveVehicleExitFlow(Vehicle vehicle) {
		Optional<Slot> optSlot = this.slotDao.findByVehicleId(vehicle.getId());
		Slot savedSlot = optSlot.get();
		Optional<Vehicle> optVehicle = this.vehicleDao.findById(vehicle.getId());
		if (optVehicle.isPresent()) {
			Vehicle savedVehicle = optVehicle.get();

			// Updating vehicle info
			VehicleInfo vehicleInfo = savedVehicle.getInfo();
			vehicleInfo.setOutTime(new Date(System.currentTimeMillis()));
			vehicleInfo.setAmount(2.0); //TODO : Amount calculation strategy
			this.vehicleInfoDao.save(vehicleInfo);

			// Making an entry in Vehicle Entry table. We are creating it only when vehicle
			// exits the parking lot
			VehicleEntry entry = new VehicleEntry();
			entry.setInTime(vehicleInfo.getInTime());
			entry.setOutTime(vehicleInfo.getOutTime());
			entry.setVehicle(savedVehicle);
			entry.setSlot(savedSlot);
			this.vehicleEntryDao.save(entry);

			// Update slot entry and setting vehicle to null
			
			savedSlot.setIsFree(true);
			savedSlot.setVehicle(null);
			this.slotDao.save(savedSlot);
			parkingSlotMap.remove(savedSlot.getId());
			slotIdSet.add(savedSlot.getId());
		} else {
			throw new ParkingSystemException("Vehicle entry does not exist in system !");
		}
	}

	@Override
	public Optional<List<VehicleEntry>> getAllVehicleEntries() {
		return Optional.ofNullable(this.vehicleEntryDao.findAll());
	}

	private void saveVehicle(Vehicle vehicle) {
		this.vehicleDao.save(vehicle);
	}

	private Optional<Long> getAvailableSlot(Vehicle vehicle) {
		while (!slotIdSet.isEmpty()) {
			Long id = slotIdSet.pollFirst();
			Slot s = new Slot();
			s.setId(id);
			s.setVehicle(vehicle);
			s.setIsFree(false);
			parkingSlotMap.putIfAbsent(id, s);
			if (parkingSlotMap.containsKey(id) && parkingSlotMap.get(id).getVehicle().equals(vehicle)) {
				return Optional.of(id);
			}
				
		}
		return Optional.empty();	

	}

}
