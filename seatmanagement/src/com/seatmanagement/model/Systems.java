package com.seatmanagement.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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

	@Column(name = "networ_type")
	private Integer networkType;
	
	@Column(name = "allotment_status")
    private String allotmentStatus;
	
	@OneToOne(cascade=CascadeType.ALL)  
    @JoinColumn(name="employee_id")  
    private Employee employee;

	public UUID getSystemId() {
		return systemId;
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

	public Integer getNetworkType() {
		return networkType;
	}

	public void setNetworkType(Integer networkType) {
		this.networkType = networkType;
	}

	public String getAllotmentStatus() {
		return allotmentStatus;
	}

	public void setAllotmentStatus(String allotmentStatus) {
		this.allotmentStatus = allotmentStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}	
	
}
