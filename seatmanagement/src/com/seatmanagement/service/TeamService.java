package com.seatmanagement.service;

import java.util.List;
import java.util.UUID;

import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Team;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         Provides interface for all business logic related processing to
 *         'Team' model object
 *
 */
public interface TeamService {

	/**
	 * 
	 * @param team
	 * @param organisationId
	 */
	public void saveTeam(Team team, UUID organisationId);

	/**
	 * 
	 * @return List<Team>
	 */
	public List<Team> getAll();

	/**
	 * 
	 * @param teamName
	 * @return Team
	 * @throws BusinessException 
	 */
	public Team getTeamByName(String teamName) throws BusinessException;

	/**
	 * 
	 * @param teamId
	 * @return Team
	 * @throws BusinessException 
	 */
	public Team getTeamById(UUID teamId) throws BusinessException;

	/**
	 * 
	 * @param team
	 */
	public void updateTeam(Team team);

	/**
	 * 
	 * @param team
	 */
	public void deleteTeam(Team team);

	/**
	 * 
	 * @param TeamId
	 */
	public void deleteTeamById(UUID TeamId);

}
