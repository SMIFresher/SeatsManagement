package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Team;
import com.seatmanagement.service.TeamService;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         This class gets all requests for 'Team' model object and delegates to
 *         service classes for business processing
 *
 */
@Controller
@RequestMapping("/Teams")
public class TeamController {

	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	private TeamService teamService;

	/**
	 * 
	 * @param team
	 * @param organisationId
	 * @return ResponseEntity
	 * @throws BusinessException
	 */
	@RequestMapping(value="/{organisationId}", method = RequestMethod.POST)
	public ResponseEntity saveTeam(Team team, @PathVariable(value = "organisationId") UUID organisationId)
			throws BusinessException {

		logger.info(
				"Controller: TeamController Method : saveTeam request processing started at : " + LocalDateTime.now());

		ResponseEntity model = null;

		if (Objects.isNull(team)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		teamService.saveTeam(team, organisationId);

		model = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: TeamController Method : saveTeam response sent at : " + LocalDateTime.now());

		return model;
	}

	/**
	 * 
	 * @return ResponseEntity
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<Team>> getAll() {
		logger.info(
				"Controller: TeamController Method : getAll request processing started at : " + LocalDateTime.now());
		
		ResponseEntity model = null;

		List<Team> teams = teamService.getAll();

		model = new ResponseEntity(teams, HttpStatus.OK);
		
		logger.info(
				"Controller: TeamController Method : getAll response sent at : " + LocalDateTime.now());
		return model;
	}

	/**
	 * 
	 * @param teamId
	 * @return ResponseEntity
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/TeamById/{teamId}",method=RequestMethod.GET)
	public ResponseEntity TeamById(@PathVariable("teamId") UUID teamId) throws BusinessException {

		logger.info("Controller: TeamController Method : getTeamById request processing started at : "
				+ LocalDateTime.now());
		ResponseEntity response = null;
		List<Team> team = null;
		
		if (null == teamId) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		
	   team = (List<Team>) teamService.getTeamById(teamId);
		response = new ResponseEntity(team, HttpStatus.OK);


		logger.info("Controller: TeamController Method : getTeamById response sent at : " + LocalDateTime.now());

		return response;
	}


	/**
	 * 
	 * @param teamId
	 * @return ResponseEntity
	 * @throws BusinessException
	 */
	@RequestMapping(value="/deleteTeamById/{teamId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteTeamById(@PathVariable("teamId") UUID teamId) throws BusinessException {

		logger.info("Controller: TeamController Method : deleteTeam request processing started at : "
				+ LocalDateTime.now());

		ResponseEntity responseEntity = null;

		if (Objects.isNull(teamId)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		teamService.deleteTeamById(teamId);

		responseEntity = new ResponseEntity(HttpStatus.OK);

		logger.info("Controller: OrganisationController Method : deleteOrganisation response sent at : "
				+ LocalDateTime.now());

		return responseEntity;
	}

	/**
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/TeamView")
	public ModelAndView getTeamView() {

		logger.info("Controller: TeamController Method : getTeamView request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = null;

		model = new ModelAndView("HR/team");

		logger.info("Controller: TeamController Method : getTeamView response sent at : " + LocalDateTime.now());

		return model;
	}

	/**
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/TeamTB")
	public ModelAndView getTeamTB() {

		logger.info(
				"Controller: TeamController Method : getTeamTB request processing started at : " + LocalDateTime.now());

		ModelAndView model = null;

		model = new ModelAndView("Curds/TeamTbCURD");

		logger.info("Controller: TeamController Method : getTeamTB response sent at : " + LocalDateTime.now());

		return model;
	}
	
	/**
	 * 
	 * @param team
	 * @return ModelAndView
	 * @throws BusinessException
	 */
/*	@RequestMapping("/updateTeam")
	public ModelAndView updateTeam(@ModelAttribute Team team) throws BusinessException {

		logger.info("Controller: TeamController Method : updateTeam request processing started at : "
				+ LocalDateTime.now());

		ModelAndView model = null;
		
		if (null == team) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		model = new ModelAndView();

		teamService.updateTeam(team);

		logger.info("Controller: TeamController Method : updateTeam response sent at : " + LocalDateTime.now());

		return model;
	}
*/
	/**
	 * 
	 * @param team
	 * @return ModelAndView
	 * @throws BusinessException
	 */
/*	@RequestMapping("/deleteTeam")
	public ResponseEntity deleteTeam(Team team) throws BusinessException {

		logger.info("Controller: TeamController Method : deleteTeam request processing started at : "
				+ LocalDateTime.now());


		ResponseEntity model = null;
		
		if (null == team) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		teamService.deleteTeam(team);

		model = new ResponseEntity(HttpStatus.OK);
		
      logger.info("Controller: TeamController Method : deleteTeam response sent at : " + LocalDateTime.now());

		return model;
	}*/

	/**
	 * 
	 * @param teamName
	 * @return ResponseEntity
	 * @throws BusinessException
	 *//*
	@RequestMapping("/getTeamByName")
	public ResponseEntity getTeamByName(@ModelAttribute String teamName) throws BusinessException {

		logger.info("Controller: TeamController Method : getTeamByName request processing started at : "
				+ LocalDateTime.now());

		String jsonResponse = null;

		if (StringUtils.isBlank(teamName)) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}

		Team team = teamService.getTeamByName(teamName);

		jsonResponse = new Gson().toJson(team);

		logger.info("Controller: TeamController Method : getTeamByName response sent at : " + LocalDateTime.now());

		return ResponseEntity.ok(jsonResponse);
	}
*/

}
