package com.seatmanagement.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name="xaxis")
	private String x_axis;
	
	@Column(name="yaxis")
	private String y_axis;
	
	@OneToOne
	@JoinColumn(name="block_id")
	@JsonIgnore
	private Block block;
	
	/*@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="team_id")
	private Team team;*/
	
	@OneToMany(mappedBy="seating")
	@JsonIgnore
    private  Set<SeatingDetails> seatingDetails;


	public String getX_axis() {
		return x_axis;
	}

	public void setX_axis(String x_axis) {
		this.x_axis = x_axis;
	}

	public String getY_axis() {
		return y_axis;
	}

	public void setY_axis(String y_axis) {
		this.y_axis = y_axis;
	}

	public Set<SeatingDetails> getSeatingDetails() {
		return seatingDetails;
	}

	public void setSeatingDetails(Set<SeatingDetails> seatingDetails) {
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
