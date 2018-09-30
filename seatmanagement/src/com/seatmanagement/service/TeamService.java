package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.util.MultiValueMap;

import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Team;

public interface TeamService {

	public void saveTeam(Team team, UUID organisationId);
	
	public List<Team> getAll();

	public Team getTeamByName(String teamName);

	public Team getTeamById(String teamId);

	public void updateTeam(Team team);

	public void deleteTeam(Team team);

	public void deleteTeamById(UUID TeamId);

}
