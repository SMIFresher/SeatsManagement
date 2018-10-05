package com.seatmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatmanagement.dao.EmployeeDao;
import com.seatmanagement.dao.GenericDao;
import com.seatmanagement.dao.TeamDao;
import com.seatmanagement.exception.ApplicationException;
import com.seatmanagement.model.Employee;
import com.seatmanagement.model.Organisation;
import com.seatmanagement.model.Team;
import com.seatmanagement.service.TeamService;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class provides implementation for all business logic related
 *         processing to 'Team' model object
 *
 */
@Service
public class TeamServiceImpl implements TeamService {

	private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void saveTeam(Team team, UUID organisationId) {
		logger.info("Service: TeamServiceImpl Method : saveTeam started at : " + LocalDateTime.now());

		Organisation organisation = new Organisation();
		organisation.setOrganisationId(organisationId);
		team.setOrganisation(organisation);

		Employee teamHead = new Employee();
		teamHead = (Employee) genericDao.getById(teamHead, team.getTeamHeadEmployeeId());

		if (Objects.isNull(teamHead)) {
			throw new ApplicationException("Employee Record not found for UUID : " + team.getTeamHeadEmployeeId());
		}

		team.setTeamHead(teamHead.getFirstName());
		genericDao.saveOrUpdate(team);

		// update teamHead
		teamHead.setTeam(team);
		genericDao.saveOrUpdate(teamHead);

		logger.info("Service: TeamServiceImpl Method : saveTeam ended at : " + LocalDateTime.now());
	}

	@Override
	public List<Team> getAll() {
		logger.info("Service: TeamServiceImpl Method : getAll started at : " + LocalDateTime.now());
		// TODO Auto-generated method stub
		Team team = new Team();
		List<Team> teams = genericDao.getAll(team);

		// set team count
		if (Objects.nonNull(teams) && !teams.isEmpty()) {
			for (Team teamObj : teams) {
				Set<Employee> employees = teamObj.getEmployees();
				if (Objects.nonNull(employees)) {
					teamObj.setTeamMembersCount(employees.size());
				}
			}
		}
		
		logger.info("Service: TeamServiceImpl Method : getAll ended at : " + LocalDateTime.now());

		return teams;
	}

	@Override
	public Team getTeamByName(String teamName) {
		logger.info("Service: TeamServiceImpl Method : getTeamByName started at : " + LocalDateTime.now());

		Team team = teamDao.getTeamByName(teamName);

		logger.info("Service: TeamServiceImpl Method : getTeamByName ended at : " + LocalDateTime.now());

		return team;
	}

	@Override
	public Team getTeamById(UUID teamId) {
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
		logger.info(
				"Service: TeamServiceImpl Method : deleteTeamById started at : " + LocalDateTime.now());

		Team team = new Team();

		team.setTeamId(teamId);

		genericDao.delete(team);

		logger.info(
				"Service: TeamServiceImpl Method : deleteTeamById ended at : " + LocalDateTime.now());
	}

}
