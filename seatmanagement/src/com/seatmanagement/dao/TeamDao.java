package com.seatmanagement.dao;

import com.seatmanagement.exception.BusinessException;
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
	 * @throws BusinessException 
	 */
	public Team getTeamByName(String teamName) throws BusinessException;

}
