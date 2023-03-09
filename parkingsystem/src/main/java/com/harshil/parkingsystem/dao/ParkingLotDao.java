package com.harshil.parkingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshil.parkingsystem.models.ParkingLot;

public interface ParkingLotDao extends JpaRepository<ParkingLot, Long> {

}