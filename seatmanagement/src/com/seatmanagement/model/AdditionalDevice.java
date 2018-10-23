package com.seatmanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="additional_device")
public class AdditionalDevice implements Serializable {
	

	private static final long serialVersionUID = -6615735067436384024L;

	@Id
	@Column(name = "additional_device_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID additional_device_id;
	
	@NotEmpty(message = "Device name can not be empty")
	@NotNull(message = "Device name can not be null")
	@Column(name="device_name")
	private String device_name;

	
	@ManyToMany(mappedBy = "additionalDevice")
	@JsonIgnore
    private Set<Systems> systems;
	
	public Set<Systems> getSystems() {
		return systems;
	}

	public void setSystems(Set<Systems> systems) {
		this.systems = systems;
	}

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
