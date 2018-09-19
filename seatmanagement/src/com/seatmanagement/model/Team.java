package com.seatmanagement.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "team")
public class Team implements Serializable{

	@Id
	@Column(name = "team_id")
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID teamId;

	@Column(name = "team_name")
	private String teamName;

	@Column(name = "team_head")
	private String teamHead;

	@Column(name = "team_members_count")
	private Integer teamMembersCount;
	
	@OneToMany(mappedBy="team")
    private Set<Employee> items;

	public UUID getTeamId() {
		return teamId;
	}

	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamHead() {
		return teamHead;
	}

	public void setTeamHead(String teamHead) {
		this.teamHead = teamHead;
	}

	public Integer getTeamMembersCount() {
		return teamMembersCount;
	}

	public void setTeamMembersCount(Integer teamMembersCount) {
		this.teamMembersCount = teamMembersCount;
	}

}
