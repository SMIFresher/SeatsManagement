package com.seatmanagement.dao;

import java.util.UUID;

import com.seatmanagement.model.Team;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         Provides interface for all Database related activities to 'Team'
 *         model object
 *
 */
public interface TeamDao {

	/**
	 * 
	 * @param teamName
	 * @return Team
	 */
	public Team getTeamByName(String teamName);

	/* public List<Team> getAll(); */

	/**
	 * 
	 * @param teamId
	 * @return Team
	 */
	public Team getTeamById(UUID teamId);

	/* public void deleteTeamById(UUID teamId); */

}
