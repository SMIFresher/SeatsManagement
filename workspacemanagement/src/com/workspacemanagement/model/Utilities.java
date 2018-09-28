package com.workspacemanagement.model;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

	@Entity
	@Table(name="utilities")
	public class Utilities implements Serializable{


	
		private static final long serialVersionUID = -8172143925834195501L;

		@Id
		@Column(name = "utility_id")
		@Type(type = "uuid-char")
		@GeneratedValue(generator = "uuid")
		@GenericGenerator(name = "uuid", strategy = "uuid2")
		private UUID utilityId;

		@Column(name="utility_name")
	    private String utilityName;
		
		@ManyToMany(mappedBy = "utilities")
		@JsonIgnore
	    private Set<Block> block;

		public Set<Block> getBlock() {
			return block;
		}

		public void setBlock(Set<Block> block) {
			this.block = block;
		}

		public UUID getUtilityId() {
			return utilityId;
		}

		public void setUtilityId(UUID utilityId) {
			this.utilityId = utilityId;
		}

		public String getUtilityName() {
			return utilityName;
		}

		public void setUtilityName(String utilityName) {
			this.utilityName = utilityName;
		}
		
		
	    
		
}
