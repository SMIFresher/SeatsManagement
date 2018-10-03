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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
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
	
	@Column(name="seating_accessories")
	private String seatingAccessories;
	
	@Column(name="seating_row")
	private String seatingRow;
	
	@Column(name="seating_colum")
	private String seatingColum;
	
	@Column(name="seating_system_no")
	private String seatingSystemNo;


	public String getSeatingAccessories() {
		return seatingAccessories;
	}

	public void setSeatingAccessories(String seatingAccessories) {
		this.seatingAccessories = seatingAccessories;
	}

	public String getSeatingRow() {
		return seatingRow;
	}

	public void setSeatingRow(String seatingRow) {
		this.seatingRow = seatingRow;
	}

	public String getSeatingColum() {
		return seatingColum;
	}

	public void setSeatingColum(String seatingColum) {
		this.seatingColum = seatingColum;
	}

	public String getSeatingSystemNo() {
		return seatingSystemNo;
	}

	public void setSeatingSystemNo(String seatingSystemNo) {
		this.seatingSystemNo = seatingSystemNo;
	}

	@OneToOne(mappedBy="seatingDetails")
	@JsonIgnore
	private Reallocation reallocation;
	
	public Reallocation getReallocation() {
		return reallocation;
	}

	public void setReallocation(Reallocation reallocation) {
		this.reallocation = reallocation;
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

	public String getSeatingDate() {
		return seatingDate;
	}

	public void setSeatingDate(String seatingDate) {
		this.seatingDate = seatingDate;
	}
}
