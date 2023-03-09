package com.harshil.parkingsystem.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "parkinglot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParkingLot implements Serializable {

	private static final long serialVersionUID = 2723711740460904592L;

	private Long id;


	@Id
	@GeneratedValue(generator = "parkinglot_generator")
	@SequenceGenerator(name = "parkinglot_generator", sequenceName = "parkinglot_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingLot other = (ParkingLot) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
