package com.seatmanagement.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seatmanagement.service.AdditionalDeviceService;

@Entity
@Table(name = "system")
public class Systems implements Serializable{
	

	private static final long serialVersionUID = 2009937655758891547L;

	@Id
	@Column(name = "system_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID systemId;

	@Column(name = "system_name")
	private String systemName;

	@Column(name = "system_type")
	private String systemType;

	@Column(name = "network_type")
	private String networkType;
	
	@Column(name = "allotment_status")
    private String allotmentStatus;
	

	
	@OneToOne(targetEntity=Employee.class,cascade=CascadeType.ALL)  
    @JoinColumn(name="employee_id")  
    private Employee employee;
	
	@ManyToMany(fetch=FetchType.EAGER,targetEntity = AdditionalDevice.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "system_additional_device", 
				joinColumns = { @JoinColumn(name = "system_id") }, 
				inverseJoinColumns = { @JoinColumn(name = "additional_device_id") })
	
	private Set<AdditionalDevice> additionalDevice;
	
	public Set<AdditionalDevice> getAdditionalDevice() {
		return additionalDevice;
	}

	public void setAdditionalDevice(Set<AdditionalDevice> additionalDevice) {
		this.additionalDevice = additionalDevice;
	}
	
	public UUID getSystemId() {
		return systemId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setSystemId(UUID systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String  networkType) {
		this.networkType = networkType;
	}

	public String getAllotmentStatus() {
		return allotmentStatus;
	}

	public void setAllotmentStatus(String allotmentStatus) {
		this.allotmentStatus = allotmentStatus;
	}
	
	
	
	
	
	

	

	
}
