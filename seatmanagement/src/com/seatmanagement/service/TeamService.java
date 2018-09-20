package com.seatmanagement.service;

import java.util.Map;

import com.seatmanagement.model.Team;

public interface TeamService {

	public void saveTeam(Team team);

	public Team getTeamByName(String teamName);

	public Team getTeamById(String teamId);

	public void updateTeam(Team team);

	public void deleteTeam(Team team);

	public void deleteTeamById(String teamId);

}
