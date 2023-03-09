package com.harshil.parkingsystem.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vehicleinfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehicleInfo implements Serializable {

	private static final long serialVersionUID = 4866416163867005563L;

	private Long id;
	private Date inTime;
	private Date outTime;
	private Double amount;

	@Id
	@GeneratedValue(generator = "vehicleinfo_generator")
	@SequenceGenerator(name = "vehicleinfo_generator", sequenceName = "vehicleinfo_sequence", initialValue = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Column(name = "outtime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inTime == null) ? 0 : inTime.hashCode());
		result = prime * result + ((outTime == null) ? 0 : outTime.hashCode());
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
		VehicleInfo other = (VehicleInfo) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return true;
	}

}
