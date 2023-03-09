package com.harshil.parkingsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshil.parkingsystem.models.Slot;

public interface SlotDao extends JpaRepository<Slot, Long> {

	Optional<Slot> findByVehicleId(Long id);

}
