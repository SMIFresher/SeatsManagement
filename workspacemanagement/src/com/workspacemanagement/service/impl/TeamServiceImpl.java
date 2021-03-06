package com.workspacemanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.dao.TeamDao;
import com.workspacemanagement.model.Floor;
import com.workspacemanagement.model.Organisation;
import com.workspacemanagement.model.Team;
import com.workspacemanagement.service.TeamService;

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
	public List<Team> getAll() {
		// TODO Auto-generated method stub
	return teamDao.getAll();
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
	public void deleteTeamById(UUID teamId) {
		logger.info("Service: OrganisationServiceImpl Method : deleteOrganisationById started at : " + LocalDateTime.now());
		
		Team team = new Team();
		
		team.setTeamId(teamId);
		
		genericDao.delete(team);

		logger.info("Service: OrganisationServiceImpl Method : deleteOrganisationById ended at : " + LocalDateTime.now());		
	}

}
