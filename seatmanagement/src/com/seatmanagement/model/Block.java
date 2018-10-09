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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Adithya Prabhu
 * 
 * Model object to represent a building
 *
 */
@Entity
@Table(name="block")
public class Block implements Serializable{


	private static final long serialVersionUID = 8650783024313379209L;

	@Id
	@Column(name = "block_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID blockId;

	@Column(name="block_name")
    private String blockName;
    
	@Column(name="block_type")
    private String blockType;
    
	@Column(name="block_capacity")
    private String blockCapacity;
	
	@Column(name="block_description")
    private String blockDescription;
	
	@Column(name="block_measurement")
    private String blockMeasurement;
	
	@Transient
	private String blockMeasurementString;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="floor_id", nullable=true)
    private Floor floor;
	
	@OneToMany(mappedBy="block")
	@JsonIgnore
	private List<Reallocation> reallocations;
	
	@ManyToMany(fetch=FetchType.EAGER,targetEntity = Utilities.class)
	@JoinTable(name = "block_utilities", 
				joinColumns = { @JoinColumn(name = "block_id") }, 
				inverseJoinColumns = { @JoinColumn(name = "utility_id") })
	private Set<Utilities> utilities;
	
	
	
	public Set<Utilities> getUtilities() {
		return utilities;
	}

	public void setUtilities(Set<Utilities> utilities) {
		this.utilities = utilities;
	}
	public String getBlockMeasurementString() {
		return blockMeasurementString;
	}

	public void setBlockMeasurementString(String blockMeasurementString) {
		this.blockMeasurementString = blockMeasurementString;
	}

	public List<Reallocation> getReallocations() {
		return reallocations;
	}

	public void setReallocations(List<Reallocation> reallocations) {
		this.reallocations = reallocations;
	}

	public UUID getBlockId() {
		return blockId;
	}

	public void setBlockId(UUID blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getBlockType() {
		return blockType;
	}

	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}

	public String getBlockCapacity() {
		return blockCapacity;
	}

	public void setBlockCapacity(String blockCapacity) {
		this.blockCapacity = blockCapacity;
	}

	public String getBlockDescription() {
		return blockDescription;
	}

	public void setBlockDescription(String blockDescription) {
		this.blockDescription = blockDescription;
	}

	public String getBlockMeasurement() {
		return blockMeasurement;
	}

	public void setBlockMeasurement(String blockMeasurement) {
		this.blockMeasurement = blockMeasurement;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}
		
}
