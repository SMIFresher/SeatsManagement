package com.workspacemanagement.dao;

import java.util.List;
import java.util.UUID;

import com.workspacemanagement.model.Floor;
import com.workspacemanagement.model.Team;

public interface TeamDao {

	public Team getTeamByName(String teamName);
	
	public List<Team> getAll();

	public Team getTeamById(String teamId);

	public void deleteTeamById(UUID teamId);

}
