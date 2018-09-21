package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.seatmanagement.exception.BusinessException;
import com.seatmanagement.model.Constant;
import com.seatmanagement.model.Team;
import com.seatmanagement.service.TeamService;

@Controller
public class TeamController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	private TeamService teamService;

	@RequestMapping("saveTeam")
	public ModelAndView saveTeam(@ModelAttribute Team team) {
		
		logger.info("Controller: TeamController Method : saveTeam request processing started at : " + LocalDateTime.now());
		
		ModelAndView model = null;
				
		try {
			if(Objects.isNull(team)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}

			teamService.saveTeam(team);

			model = new ModelAndView();
			
		}catch(Exception e) {
			logger.error("Exception at Controller: TeamController Method : saveTeam " + e.getMessage());
    		logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}
		
		logger.info("Controller: TeamController Method : saveTeam response sent at : " + LocalDateTime.now());
		
		return model;
	}

	@RequestMapping("getTeamByName")
	public ResponseEntity getTeamByName(@ModelAttribute String teamName) {

		logger.info("Controller: TeamController Method : getTeamByName request processing started at : " + LocalDateTime.now());
		
		String jsonResponse = null;
		
		try {
			if(StringUtils.isBlank(teamName)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}
			
			Team team = teamService.getTeamByName(teamName);
			
			jsonResponse = new Gson().toJson(team);
		}catch(Exception e) {
			logger.error("Exception at Controller: TeamController Method : getTeamByName " + e.getMessage());
    		logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}
		
		logger.info("Controller: TeamController Method : getTeamByName response sent at : " + LocalDateTime.now());
		
		return ResponseEntity.ok(jsonResponse);
	}
	
	@RequestMapping("getTeamById")
	public ResponseEntity getTeamById(@ModelAttribute String teamId) {
		
		logger.info("Controller: TeamController Method : getTeamById request processing started at : " + LocalDateTime.now());
		
		String jsonResponse = null;
				
		try {
			if(StringUtils.isBlank(teamId)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}
			
			Team team = teamService.getTeamById(teamId);

			jsonResponse = new Gson().toJson(team);
		}catch(Exception e) {
			logger.error("Exception at Controller: TeamController Method : getTeamById " + e.getMessage());
    		logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}
		
		logger.info("Controller: TeamController Method : getTeamById response sent at : " + LocalDateTime.now());
		
		return ResponseEntity.ok(jsonResponse);
	}
	
	@RequestMapping("updateTeam")
	public ModelAndView updateTeam(@ModelAttribute Team team) {
		
		logger.info("Controller: TeamController Method : updateTeam request processing started at : " + LocalDateTime.now());
		
		ModelAndView model = null;
		
		try {
			model = new ModelAndView();
			
			teamService.updateTeam(team);
		}catch(Exception e) {
			logger.error("Exception at Controller: TeamController Method : updateTeam " + e.getMessage());
    		logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}
		if(null == team) {
			throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
		}
		
		logger.info("Controller: TeamController Method : updateTeam response sent at : " + LocalDateTime.now());
		
		return model;
	}
	
	@RequestMapping("deleteTeam")
	public ModelAndView deleteTeam(@ModelAttribute Team team) {
		
		logger.info("Controller: TeamController Method : deleteTeam request processing started at : " + LocalDateTime.now());
		
		ModelAndView model = null;
		
		try {
			
			if(null == team) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}
			
			model = new ModelAndView();
			teamService.deleteTeam(team);
		}catch(Exception e) {
			logger.error("Exception at Controller: TeamController Method : deleteTeam " + e.getMessage());
    		logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}
		
		logger.info("Controller: TeamController Method : deleteTeam response sent at : " + LocalDateTime.now());
		
		return model;
	}
	
	@RequestMapping("deleteTeamById")
	public ModelAndView deleteTeamById(@ModelAttribute String teamId) {
		
		logger.info("Controller: TeamController Method : deleteTeamById request processing started at : " + LocalDateTime.now());
		
		ModelAndView model = null;
		
		try {
			if(StringUtils.isBlank(teamId)) {
				throw new BusinessException(Constant.REQUIRED_PARAMAS_NOT_PRESENT);
			}
			
			model = new ModelAndView();
			teamService.deleteTeamById(teamId);
		}catch(Exception e) {
			logger.error("Exception at Controller: TeamController Method : deleteTeamById " + e.getMessage());
    		logger.error("Exception stack : ", e);
			throw new RuntimeException(e);
		}
		
		logger.info("Controller: TeamController Method : deleteTeamById response sent at : " + LocalDateTime.now());
		
		return model;
	}
}
