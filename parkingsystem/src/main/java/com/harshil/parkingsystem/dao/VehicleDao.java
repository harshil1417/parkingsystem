package com.harshil.parkingsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshil.parkingsystem.models.Vehicle;

public interface VehicleDao extends JpaRepository<Vehicle, Long> {

	Optional<Vehicle> findByNumber(String number);

}
