package com.seatmanagement.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="seating_detail")
public class SeatingDetails {

	@Id
	@Column(name = "seating_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID seatingId;
	
	@Column(name = "system_id")
	@Type(type = "uuid-char")
	private UUID systemId;
	
	@Column(name="seating_date")
	private String seatingDate;
	
	@Column(name="x_axis")
	private String xAxis;
	
	@Column(name="y_axis")
	private String yAxis;

	public UUID getSeatingId() {
		return seatingId;
	}

	public void setSeatingId(UUID seatingId) {
		this.seatingId = seatingId;
	}

	public UUID getSystemId() {
		return systemId;
	}

	public void setSystemId(UUID systemId) {
		this.systemId = systemId;
	}

	public String getSeatingDate() {
		return seatingDate;
	}

	public void setSeatingDate(String seatingDate) {
		this.seatingDate = seatingDate;
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	public String getyAxis() {
		return yAxis;
	}

	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}
	
}
