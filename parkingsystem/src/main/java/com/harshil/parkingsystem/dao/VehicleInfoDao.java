package com.harshil.parkingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshil.parkingsystem.models.VehicleInfo;

public interface VehicleInfoDao extends JpaRepository<VehicleInfo, Long> {

}
