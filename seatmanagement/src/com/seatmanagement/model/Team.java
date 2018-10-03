package com.seatmanagement.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Transient
	private UUID teamHeadEmployeeId;

	@Transient
	private Integer teamMembersCount;
	
	@OneToMany(mappedBy="team", fetch=FetchType.EAGER)
	@JsonIgnore
    private Set<Employee> employees;
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
    private Organisation organisation;

	public UUID getTeamHeadEmployeeId() {
		return teamHeadEmployeeId;
	}

	public void setTeamHeadEmployeeId(UUID teamHeadEmployeeId) {
		this.teamHeadEmployeeId = teamHeadEmployeeId;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
