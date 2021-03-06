
package com.seatmanagement.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="floor")
public class Floor implements Serializable{
	
	private static final long serialVersionUID = 8650783024313379209L;
	@Id
	@Column(name = "floor_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID floorId;

    @Column(name="floor_type")
    private String floorType;
    
    @Column(name="floor_name")
    private String floorName;
    
  
    @ManyToOne
	@JoinColumn(name = "building_id", nullable = false)
	private Building building;
	
    @OneToMany(mappedBy="floor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Block> blocks;
    
	public Set<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(Set<Block> blocks) {
		this.blocks = blocks;
	}

	public UUID getFloorId() {
		return floorId;
	}

	public void setFloorId(UUID floorId) {
		this.floorId = floorId;
	}

	public String getFloorType() {
		return floorType;
	}

	public void setFloorType(String floorType) {
		this.floorType = floorType;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}


}
