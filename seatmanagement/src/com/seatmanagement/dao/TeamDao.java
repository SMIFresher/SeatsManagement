package com.seatmanagement.dao;

import com.seatmanagement.model.Team;

public interface TeamDao {

	public Team getTeamByName(String teamName);

	public Team getTeamById(String teamId);

	public void deleteTeamById(String teamId);

}
