package com.seatmanagement.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.TeamDao;
import com.seatmanagement.model.Team;
import com.seatmanagement.service.TeamService;

public class TeamServiceImpl implements TeamService{

	@Autowired
	private GenericDao genericDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Override
	public void saveTeam(Team team) {
		genericDao.saveOrUpdate(team);
	}

	@Override
	public Team getTeamByName(String teamName) {
		Team team = teamDao.getTeamByName(teamName);
		return team;
	}

	@Override
	public Team getTeamById(String teamId) {
		Team team = teamDao.getTeamById(teamId);
		return team;
	}

	@Override
	public void updateTeam(Team team) {
		genericDao.saveOrUpdate(team);
	}

	@Override
	public void deleteTeam(Team team) {
		genericDao.delete(team);
	}

	@Override
	public void deleteTeamById(String teamId) {
		teamDao.deleteTeamById(teamId);
	}

}
