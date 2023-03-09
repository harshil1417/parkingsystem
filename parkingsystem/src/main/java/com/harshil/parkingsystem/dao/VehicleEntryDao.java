package com.harshil.parkingsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshil.parkingsystem.models.VehicleEntry;

public interface VehicleEntryDao extends JpaRepository<VehicleEntry, Long> {

	Optional<VehicleEntry> findByVehicleId(Long id);

}
