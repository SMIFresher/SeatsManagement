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
@Table(name="seating")
public class Seating implements Serializable {
	
	private static final long serialVersionUID = -3552137905378520101L;
	
	@Id
	@Column(name = "seating_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID seatingId;
	
	@Column(name="seat_occupied")
	private int seat_occupied;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="block_id")
	private Block block;
	
	/*@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="team_id")
	private Team team;*/
	
	@OneToOne(cascade=CascadeType.ALL)  
    @JoinColumn(name="seating_id")  
    private  SeatingDetails seatingDetails;

	

	public SeatingDetails getSeatingDetails() {
		return seatingDetails;
	}

	public void setSeatingDetails(SeatingDetails seatingDetails) {
		this.seatingDetails = seatingDetails;
	}

	public UUID getSeatingId() {
		return seatingId;
	}

	public void setSeatingId(UUID seatingId) {
		this.seatingId = seatingId;
	}



	public int getSeat_occupied() {
		return seat_occupied;
	}

	public void setSeat_occupied(int seat_occupied) {
		this.seat_occupied = seat_occupied;
	}



	/*public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}*/

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
}
