package com.workspacemanagement.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.workspacemanagement.model.Constant;

@Controller
public class ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	@RequestMapping(value = "errors")
	public ModelAndView renderErrorPageForNonAJAX(HttpServletRequest httpRequest) {
		
		
		
		ModelAndView errorPage = new ModelAndView("/errorpages/ErrorPage");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);
		
		switch (httpErrorCode) {
		case 400: {
			errorMsg = "Error Code: 400. Bad Request";
			break;
		}
		case 401: {
			errorMsg = "Http Error Code: 401. Unauthorized";
			break;
		}
		case 404: {
			errorMsg = "Error Code: 404. Resource not found";
			break;
		}
		case 500: {
			errorMsg = "Error Code: 500. Internal Server Error";
			break;
		}
		default:{
			errorMsg = "Error Code: "+httpErrorCode+". Server Error";
		
		}
		}
		
		logger.error("Exeception occured in Application "+errorMsg);
		
		errorPage.addObject("errorMsg", errorMsg);
		errorPage.addObject("errorcode", httpErrorCode);
		return errorPage;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
	
	//headers = "X-Requested-With=XMLHttpRequest"

	@RequestMapping(value = "errors", headers = {"Accept=application/json"})
	public @ResponseBody Map renderErrorMessagesForAJAX(HttpServletRequest httpRequest) {

		Map model = new HashMap();
		String errorCode = null;
		String errorMsg = null;
		int httpErrorCode = getErrorCode(httpRequest);

		switch (httpErrorCode) {
		case 400: {
			errorCode = "400";
			errorMsg = "Http Error Code: 400. Bad Request";
			break;
		}
		case 401: {
			errorCode = "401";
			errorMsg = "Http Error Code: 401. Unauthorized";
			break;
		}
		case 404: {
			errorCode = "404";
			errorMsg = "Http Error Code: 404. Resource not found";
			break;
		}
		case 500: {
			errorCode = "500";
			errorMsg = "Http Error Code: 500. Internal Server Error";
			break;
		}
		}

		model.put(Constant.RESPONSE_STATUS, Constant.RESPONSE_STATUS_ERROR);
		model.put(Constant.RESPONSE_MESSAGE, errorMsg);
		model.put(Constant.RESPONSE_ERROR_CODE, errorCode);

		return model;
	}
}