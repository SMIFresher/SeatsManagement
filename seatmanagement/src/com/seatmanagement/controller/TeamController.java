package com.seatmanagement.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Team;
import com.seatmanagement.service.TeamService;

@Controller
public class TeamController {

	@Autowired
	private TeamService teamService;

	@RequestMapping("saveTeam")
	public ModelAndView saveTeam(@ModelAttribute Team team) {
		
		if(null==team) {
			throw new RuntimeException("Required Params not present");
		}

		teamService.saveTeam(team);

		ModelAndView model = new ModelAndView();
		return model;
	}

	@RequestMapping("getTeamByName")
	public ModelAndView getTeamByName(@ModelAttribute String teamName) {

		if(StringUtils.isBlank(teamName)) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		
		Team team = teamService.getTeamByName(teamName);

		model.addObject("team", team);
		
		return model;
	}
	
	@RequestMapping("getTeamById")
	public ModelAndView getTeamById(@ModelAttribute String teamId) {

		if(StringUtils.isBlank(teamId)) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		
		Team team = teamService.getTeamById(teamId);

		model.addObject("team", team);
		
		return model;
	}
	
	@RequestMapping("updateTeam")
	public ModelAndView updateTeam(@ModelAttribute Team team) {

		if(null == team) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		
		teamService.updateTeam(team);
		
		return model;
	}
	
	@RequestMapping("deleteTeam")
	public ModelAndView deleteTeam(@ModelAttribute Team team) {
		if(null == team) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		teamService.deleteTeam(team);
		
		return model;
	}
	
	@RequestMapping("deleteTeamById")
	public ModelAndView deleteTeamById(@ModelAttribute String teamId) {
		if(StringUtils.isBlank(teamId)) {
			throw new RuntimeException("Required Params not present");
		}
		
		ModelAndView model = new ModelAndView();
		teamService.deleteTeamById(teamId);
		
		return model;
	}
}
