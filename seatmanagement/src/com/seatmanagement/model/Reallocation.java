package com.seatmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         Model object to represent a Reallocation request
 *
 */
@Entity
@Table(name = "reallocation")
public class Reallocation implements Serializable {
	private static final long serialVersionUID = 8650783024313379209L;

	@Id
	@Column(name = "reallocation_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID reallocationId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "previous_block_id")
	private Block previousBlock;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reallocated_block_id")
	private Block block;
	
	@Column(name = "reallocation_requested_date")
	private LocalDate reallocationRequestedDate;
	
	@Column(name = "reallocated_date")
	private LocalDate reallocatedDate;
	
	@Column(name = "reallocation_status")
	private String reallocationStatus;
	
	@Column(name = "alloted_by")
	private String allotedBy;
	
	@OneToOne(targetEntity=Employee.class)  
	@JoinColumn(name="employee_id")  
	private Employee employee;

	
	public LocalDate getReallocationRequestedDate() {
		return reallocationRequestedDate;
	}

	public void setReallocationRequestedDate(LocalDate reallocationRequestedDate) {
		this.reallocationRequestedDate = reallocationRequestedDate;
	}

	public LocalDate getReallocatedDate() {
		return reallocatedDate;
	}

	public void setReallocatedDate(LocalDate reallocatedDate) {
		this.reallocatedDate = reallocatedDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}	

	public Block getPreviousblock() {
		return previousBlock;
	}

	public void setPreviousBlock(Block previousBlock) {
		this.previousBlock = previousBlock;
	}

	public UUID getReallocationId() {
		return reallocationId;
	}

	public void setReallocationId(UUID reallocationId) {
		this.reallocationId = reallocationId;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public String getReallocationStatus() {
		return reallocationStatus;
	}

	public void setReallocationStatus(String reallocationStatus) {
		this.reallocationStatus = reallocationStatus;
	}

	public String getAllotedBy() {
		return allotedBy;
	}

	public void setAllotedBy(String allotedBy) {
		this.allotedBy = allotedBy;
	}

}
