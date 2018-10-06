package com.seatmanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seatmanagement.model.Constant;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         Second and last filter in Exception Handling. Generates appropriate
 *         error responses for AJAX and Non AJAX requests
 *
 */
@Controller
public class ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	/**
	 * 
	 * Generates error response for Non AJAX requests
	 * 
	 * @param httpRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "errors")
	public ModelAndView renderErrorPageForNonAJAX(HttpServletRequest httpRequest, HttpServletResponse response) {

		ModelAndView errorPage = new ModelAndView("/errorpages/ErrorPage");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);
		int applicationErrorCode = 0;
		HttpStatus httpStatus = null;
		String exceptionType = (String) httpRequest.getAttribute(Constant.EXCEPTION_TYPE);
		Map errorMap = new HashMap();
		HttpHeaders headers = (HttpHeaders) httpRequest.getAttribute(Constant.HTTP_HEADER);

		// populate response headers if any
		if (Objects.nonNull(headers)) {
			populateHeadersInResponse(response, headers);
		}

		httpStatus = HttpStatus.valueOf(httpErrorCode);

		if (StringUtils.isNotBlank(exceptionType)) {
			errorMsg = (String) httpRequest.getAttribute(Constant.EXCEPTION_MESSAGE);

			applicationErrorCode = populateApplicationErrorCodes(exceptionType);
		} else {
			errorMsg = generateErrorMessageFromHttpStatus(httpStatus);
		}

		logger.error("Error logged in Class : ExceptionHandler, Method : renderErrorPageForNonAJAX () at "
				+ LocalDateTime.now());
		logger.error("Error Description : " + errorMsg);

		errorMap.put(Constant.EXCEPTION_MESSAGE, errorMsg);

		if (applicationErrorCode != 0) {
			errorMap.put(Constant.RESPONSE_ERROR_CODE, applicationErrorCode);
		}

		errorPage.addObject(Constant.HTTP_ERROR_CODE, httpErrorCode);
		errorPage.addObject(Constant.ERROR_MAP, errorMap);
		errorPage.setStatus(httpStatus);

		return errorPage;
	}

	/**
	 * Generates error response for AJAX requests
	 * 
	 * @param httpRequest
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "errors", headers = { "RequestType=AJAX" })
	public ResponseEntity renderErrorMessagesForAJAX(HttpServletRequest httpRequest, HttpServletResponse response) {

		ResponseEntity model = null;
		String errorMsg = null;
		int httpErrorCode = getErrorCode(httpRequest);
		int applicationErrorCode = 0;
		HttpStatus httpStatus = null;
		Map errorMap = new HashMap();
		String exceptionType = (String) httpRequest.getAttribute(Constant.EXCEPTION_TYPE);
		HttpHeaders headers = (HttpHeaders) httpRequest.getAttribute(Constant.HTTP_HEADER);

		// populate response headers if any
		if (Objects.nonNull(headers)) {
			populateHeadersInResponse(response, headers);
		}

		httpStatus = HttpStatus.valueOf(httpErrorCode);

		if (StringUtils.isNotBlank(exceptionType)) {

			errorMsg = (String) httpRequest.getAttribute(Constant.EXCEPTION_MESSAGE);

			applicationErrorCode = populateApplicationErrorCodes(exceptionType);

		} else {
			errorMsg = generateErrorMessageFromHttpStatus(httpStatus);
		}

		logger.error("Error logged in Class : ExceptionHandler, Method : renderErrorMessagesForAJAX () at "
				+ LocalDateTime.now());
		logger.error("Error Description : " + errorMsg);

		errorMap.put(Constant.RESPONSE_ERROR_MESSAGE, errorMsg);

		if (applicationErrorCode != 0) {
			errorMap.put(Constant.RESPONSE_ERROR_CODE, applicationErrorCode);
		}

		// if any header generated add it in response
		if (Objects.nonNull(headers)) {
			model = new ResponseEntity(errorMap, headers, httpStatus);
		} else {
			model = new ResponseEntity(errorMap, httpStatus);
		}

		return model;
	}

	private String generateErrorMessageFromHttpStatus(HttpStatus httpStatus) {
		String errorMessage = httpStatus.value() + " " + httpStatus.getReasonPhrase();
		return errorMessage;
	}

	private int populateApplicationErrorCodes(String exceptionType) {
		int applicationErrorCode = 0;

		if (exceptionType.equals(Constant.EXCEPTION_TYPE_BUSINESS)) {
			applicationErrorCode = 9000;
		} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_APPLICATION)) {
			applicationErrorCode = 9001;
		} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_STANDARD_SPRING_MVC_EXCEPTION)) {
			applicationErrorCode = 9002;
		} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_RUNTIME)) {
			applicationErrorCode = 9003;
		} else if (exceptionType.equals(Constant.EXCEPTION_TYPE_EXCEPTION)) {
			applicationErrorCode = 9004;
		}
		
		return applicationErrorCode;
	}

	/**
	 * sets response headers
	 * 
	 * At the moment only used when headers are set when headers are generated
	 * while handling Standard Spring MVC Exceptions in 'ExceptionLogger' class
	 * 
	 * @param response
	 * @param headers
	 */
	private void populateHeadersInResponse(HttpServletResponse response, HttpHeaders headers) {
		List<MediaType> mediaTypes = headers.getAccept();
		Set<HttpMethod> allowedMethods = headers.getAllow();

		if (Objects.nonNull(mediaTypes)) {
			for (MediaType mediaType : mediaTypes) {
				response.addHeader(HttpHeaders.ACCEPT, mediaType.toString());
			}
		}

		if (Objects.nonNull(allowedMethods)) {
			for (HttpMethod httpMethod : allowedMethods) {
				response.addHeader(HttpHeaders.ALLOW, httpMethod.name());
			}
		}
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}