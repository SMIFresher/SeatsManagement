/*package com.seatmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
	


	@Secured({ "ROLE_LEAD", "ROLE_ADMIN" })
	@RequestMapping(value = "/index,/", method = RequestMethod.GET)
	public ModelAndView loginPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public ModelAndView logoutPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("error-login");
		return model;
	}

	@RequestMapping(value = "/pagelogout", method = RequestMethod.GET)
	public String  logoutPage(HttpServletRequest request,HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		if(authentication!=null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}

	
	private String getUser() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}



}
*/