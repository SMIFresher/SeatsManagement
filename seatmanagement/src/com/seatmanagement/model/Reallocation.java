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

	@Column(name = "previous_block_id")
	private UUID previousBlockId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reallocated_block_id")
	private Block block;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "seating_detail_id")
	private SeatingDetails seatingDetails;

	@Column(name = "reallocation_date")
	private LocalDate reallocationDate;

	@Column(name = "reallocation_status")
	private String reallocationStatus;

	@Column(name = "alloted_by")
	private String allotedBy;

	@Column(name = "reallocated_position")
	private String reallocatedPosition;

	public UUID getPreviousBlockId() {
		return previousBlockId;
	}

	public void setPreviousBlockId(UUID previousBlockId) {
		this.previousBlockId = previousBlockId;
	}

	public String getReallocatedPosition() {
		return reallocatedPosition;
	}

	public void setReallocatedPosition(String reallocatedPosition) {
		this.reallocatedPosition = reallocatedPosition;
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

	public LocalDate getReallocationDate() {
		return reallocationDate;
	}

	public SeatingDetails getSeatingDetails() {
		return seatingDetails;
	}

	public void setSeatingDetails(SeatingDetails seatingDetails) {
		this.seatingDetails = seatingDetails;
	}

	public void setReallocationDate(LocalDate reallocationDate) {
		this.reallocationDate = reallocationDate;
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
