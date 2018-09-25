package com.seatmanagement.model;

import java.io.Serializable;
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

@Entity
@Table(name="seating_detail")
public class SeatingDetails implements Serializable{


	private static final long serialVersionUID = -3552137905378520101L;

	@Id
	@Column(name = "seating_detail_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID seatingDetailsId;
	
	
	@OneToOne
    @JoinColumn(name="system_id", nullable=false)
    private Systems system;
	
	
	@OneToOne
    @JoinColumn(name="seating_id", nullable=false)
    private Seating seating;
	
	
	public Seating getSeating() {
		return seating;
	}

	public void setSeating(Seating seating) {
		this.seating = seating;
	}

	@Column(name="seating_date")
	private String seatingDate;
	
	@Column(name="seating_position")
	private String seatingPosition;
	
	
	public String getSeatingPosition() {
		return seatingPosition;
	}

	public void setSeatingPosition(String seatingPosition) {
		this.seatingPosition = seatingPosition;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "reallocatedSeatingDetailId")
	private Reallocation postReallocation;

	public Reallocation getPostReallocation() {
		return postReallocation;
	}

	public UUID getSeatingDetailsId() {
		return seatingDetailsId;
	}

	public void setSeatingDetailsId(UUID seatingDetailsId) {
		this.seatingDetailsId = seatingDetailsId;
	}

	public Systems getSystem() {
		return system;
	}

	public void setSystem(Systems system) {
		this.system = system;
	}

	public void setPostReallocation(Reallocation postReallocation) {
		this.postReallocation = postReallocation;
	}

	

	public String getSeatingDate() {
		return seatingDate;
	}

	public void setSeatingDate(String seatingDate) {
		this.seatingDate = seatingDate;
	}
}
