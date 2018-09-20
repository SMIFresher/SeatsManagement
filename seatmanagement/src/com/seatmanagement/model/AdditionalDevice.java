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
@Table(name="additional_device")
public class AdditionalDevice {
	
	@Id
	@Column(name = "additional_device_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID additional_device_id;
	
	@Column(name="device_name")
	private String device_name;

	public UUID getAdditional_device_id() {
		return additional_device_id;
	}

	public void setAdditional_device_id(UUID additional_device_id) {
		this.additional_device_id = additional_device_id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

}
