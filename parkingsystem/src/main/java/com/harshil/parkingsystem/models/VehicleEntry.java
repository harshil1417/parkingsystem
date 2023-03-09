package com.harshil.parkingsystem.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vehicleentry")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehicleEntry implements Serializable {

	private static final long serialVersionUID = 816436113837233081L;

	private long id;
	private Date inTime;
	private Date outTime;
	private Vehicle vehicle;

	private Slot slot;

	@Id
	@GeneratedValue(generator = "vehicleentry_generator")
	@SequenceGenerator(name = "vehicleentry_generator", sequenceName = "vehicleentry_sequence", initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "intime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "outtime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id", nullable = false)
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_id", nullable = false)
	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((inTime == null) ? 0 : inTime.hashCode());
		result = prime * result + ((outTime == null) ? 0 : outTime.hashCode());
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
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
		VehicleEntry other = (VehicleEntry) obj;
		if (id != other.id)
			return false;
		if (inTime == null) {
			if (other.inTime != null)
				return false;
		} else if (!inTime.equals(other.inTime))
			return false;
		if (outTime == null) {
			if (other.outTime != null)
				return false;
		} else if (!outTime.equals(other.outTime))
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}



}
