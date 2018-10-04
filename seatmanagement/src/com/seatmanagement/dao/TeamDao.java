package com.seatmanagement.dao;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.model.Floor;
import com.seatmanagement.model.Team;

public interface TeamDao {

	public Team getTeamByName(String teamName);
	
	/*public List<Team> getAll();*/

	public Team getTeamById(UUID teamId);

	/*public void deleteTeamById(UUID teamId);*/

}
