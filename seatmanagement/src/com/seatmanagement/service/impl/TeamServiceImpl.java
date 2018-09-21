package com.seatmanagement.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.TeamDao;
import com.seatmanagement.model.Team;
import com.seatmanagement.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService{
	
	private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired
	private GenericDao genericDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Override
	public void saveTeam(Team team) {
		logger.info("Service: TeamServiceImpl Method : saveTeam started at : " + LocalDateTime.now());
		
		genericDao.saveOrUpdate(team);
		
		logger.info("Service: TeamServiceImpl Method : saveTeam ended at : " + LocalDateTime.now());
	}

	@Override
	public Team getTeamByName(String teamName) {
		logger.info("Service: TeamServiceImpl Method : getTeamByName started at : " + LocalDateTime.now());
		
		Team team = teamDao.getTeamByName(teamName);
		
		logger.info("Service: TeamServiceImpl Method : getTeamByName ended at : " + LocalDateTime.now());
		
		return team;
	}

	@Override
	public Team getTeamById(String teamId) {
		logger.info("Service: TeamServiceImpl Method : getTeamById started at : " + LocalDateTime.now());
		
		Team team = teamDao.getTeamById(teamId);
		
		logger.info("Service: TeamServiceImpl Method : getTeamById ended at : " + LocalDateTime.now());
		return team;
	}

	@Override
	public void updateTeam(Team team) {
		logger.info("Service: TeamServiceImpl Method : updateTeam started at : " + LocalDateTime.now());
		
		genericDao.saveOrUpdate(team);
		
		logger.info("Service: TeamServiceImpl Method : updateTeam ended at : " + LocalDateTime.now());
	}

	@Override
	public void deleteTeam(Team team) {
		logger.info("Service: TeamServiceImpl Method : deleteTeam started at : " + LocalDateTime.now());
		
		genericDao.delete(team);
		
		logger.info("Service: TeamServiceImpl Method : deleteTeam ended at : " + LocalDateTime.now());
	}

	@Override
	public void deleteTeamById(String teamId) {
		logger.info("Service: TeamServiceImpl Method : deleteTeamById started at : " + LocalDateTime.now());
		
		teamDao.deleteTeamById(teamId);
		
		logger.info("Service: TeamServiceImpl Method : deleteTeamById ended at : " + LocalDateTime.now());
	}

}
