package com.seatmanagement.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * 
 * @author Adithya Prabhu
 * 
 * Model object to represent a building
 *
 */
@Entity
@Table(name="building")
public class Building implements Serializable{


	private static final long serialVersionUID = 8650783024313379209L;

	@Id
	@Column(name = "building_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID buildingId;

	@Column(name="building_name")
    private String buildingName;
    
	@Column(name="building_address")
    private String buildingAddress;
    
	@Column(name="building_location")
    private String buildingLocation;

	public UUID getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(UUID buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

	public String getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(String buildingLocation) {
		this.buildingLocation = buildingLocation;
	}
	
	
	
}
