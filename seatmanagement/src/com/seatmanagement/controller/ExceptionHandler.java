package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Constant;

@Controller
public class ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@RequestMapping(value = "errors")
	public ModelAndView renderErrorPageForNonAJAX(HttpServletRequest httpRequest) {

		ModelAndView errorPage = new ModelAndView("/errorpages/ErrorPage");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);
		int applicationErrorCode = 0;
		String exceptionType = (String) httpRequest.getAttribute(Constant.EXCEPTION_TYPE);
		Map errorMap = new HashMap();

		if (StringUtils.isNotBlank(exceptionType)) {

			if(!exceptionType.equals(Constant.EXCEPTION_TYPE_RUNTIME)) {
				errorMsg = (String) httpRequest.getAttribute(Constant.EXCEPTION_MESSAGE);
			}else {
				errorMsg = "Internal Server Error";
			}
			
			if (exceptionType.equals(Constant.EXCEPTION_TYPE_BUSINESS)) {
				applicationErrorCode = 9000;
			} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_APPLICATION)) {
				applicationErrorCode = 9001;
			} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_RUNTIME)) {
				applicationErrorCode = 9002;
			}

		} else {
			switch (httpErrorCode) {
			case 400: {
				errorMsg = "Http Error Code: 400. Bad Request";
				break;
			}
			case 401: {
				errorMsg = "Http Error Code: 401. Unauthorized";
				break;
			}
			case 404: {
				errorMsg = "Http Error Code: 404. Resource not found";
				break;
			}
			default: {
				errorMsg = "Http Error Code: 500. Internal Server Error";
				break;
			}
			}
		}

		logger.error("Exception caught in Class : ExceptionHandler, Method : renderErrorPageForNonAJAX () at "
				+ LocalDateTime.now());
		logger.error("Exception Description : " + errorMsg);

		errorMap.put(Constant.EXCEPTION_MESSAGE, errorMsg);
		
		if(applicationErrorCode!= 0) {
			errorMap.put(Constant.RESPONSE_ERROR_CODE, applicationErrorCode);
		}
		
		errorPage.addObject(Constant.HTTP_ERROR_CODE, httpErrorCode);
		errorPage.addObject(Constant.ERROR_MAP, errorMap);

		return errorPage;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}

	// headers = "X-Requested-With=XMLHttpRequest","RequestType=AJAX",
	// "Accept=application/json"

	@RequestMapping(value = "errors", headers = { "RequestType=AJAX" })
	public ResponseEntity renderErrorMessagesForAJAX(HttpServletRequest httpRequest) {

		ResponseEntity model = null;
		String errorMsg = null;
		int httpErrorCode = getErrorCode(httpRequest);
		int applicationErrorCode = 0;
		HttpStatus httpStatus = null;
		Map errorMap = new HashMap();

		String exceptionType = (String) httpRequest.getAttribute(Constant.EXCEPTION_TYPE);

		if (StringUtils.isNotBlank(exceptionType)) {

			if(!exceptionType.equals(Constant.EXCEPTION_TYPE_RUNTIME)) {
				errorMsg = (String) httpRequest.getAttribute(Constant.EXCEPTION_MESSAGE);
			}else {
				errorMsg = "Internal Server Error";
			}
			
			if (exceptionType.equals(Constant.EXCEPTION_TYPE_BUSINESS)) {
				applicationErrorCode = 9000;
				httpStatus = HttpStatus.CONFLICT;
			} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_APPLICATION)) {
				applicationErrorCode = 9001;
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_RUNTIME)) {
				applicationErrorCode = 9002;
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}

		} else {

			switch (httpErrorCode) {
			case 400: {
				httpStatus = HttpStatus.valueOf(httpErrorCode);
				errorMsg = "Http Error Code: 400. Bad Request";
				break;
			}
			case 401: {
				httpStatus = HttpStatus.valueOf(httpErrorCode);
				errorMsg = "Http Error Code: 401. Unauthorized";
				break;
			}
			case 404: {
				httpStatus = HttpStatus.valueOf(httpErrorCode);
				errorMsg = "Http Error Code: 404. Resource not found";
				break;
			}
			default: {
				httpStatus = HttpStatus.valueOf(500);
				errorMsg = "Http Error Code: 500. Internal Server Error";
				break;
			}
			}
		}

		logger.error("Exception caught in Class : ExceptionHandler, Method : renderErrorMessagesForAJAX () at "
				+ LocalDateTime.now());
		logger.error("Exception Description : " + errorMsg);
		
		errorMap.put(Constant.RESPONSE_ERROR_MESSAGE, errorMsg);
		
		if(applicationErrorCode == 9000 || applicationErrorCode == 9001 || applicationErrorCode == 9002) {
			errorMap.put(Constant.RESPONSE_ERROR_CODE, applicationErrorCode);
		}		
		
		model = new ResponseEntity(errorMap, httpStatus);

		return model;
	}
}