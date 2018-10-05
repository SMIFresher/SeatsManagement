package com.seatmanagement.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.seatmanagement.model.Constant;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 *         First filter in Exception handling. Class which captures all
 *         exceptions, logs them, populates the exception messages in request
 *         and rethrow the exceptions for further processing
 *
 */
@ControllerAdvice
public class ExceptionLogger extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);

	/**
	 * captures all BusinessException, logs them and populates exception messages in
	 * request for further processing
	 * 
	 * @param ex
	 * @param request
	 * @throws BusinessException
	 */
	@ExceptionHandler(value = { BusinessException.class })
	protected void handleBusinessException(BusinessException ex, WebRequest request) throws BusinessException {

		Boolean isValidationException = Boolean.FALSE;
		Errors errors = ex.getValidationErrors();
		StringBuilder exceptionMessage = null;

		if (!Objects.isNull(errors)) {
			isValidationException = Boolean.TRUE;
		}

		// If it is a ValidationException get exception messages from
		// 'org.springframework.validation.Errors' Object
		if (isValidationException) {
			List<FieldError> fieldErrors = errors.getFieldErrors();
			if (!Objects.isNull(fieldErrors) && !fieldErrors.isEmpty()) {
				exceptionMessage = new StringBuilder();
				for (FieldError fieldError : fieldErrors) {
					if (exceptionMessage.toString().isEmpty()) {
						exceptionMessage = exceptionMessage.append(fieldError.getDefaultMessage());
					} else {
						exceptionMessage = exceptionMessage.append(" ;" + fieldError.getDefaultMessage());
					}
				}
			}
		} else {
			exceptionMessage = new StringBuilder(ex.getMessage());
		}

		logger.error("BusinessException caught in Class : ExceptionLogger, Method : handleBusinessException(), at "
				+ LocalDateTime.now());
		logger.error("Exception message : " + exceptionMessage);
		logger.error("Exception stack : ", ex);

		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_BUSINESS,
				RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, exceptionMessage.toString(), RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	/**
	 * 
	 * captures all ApplicationException, logs them and populates exception messages
	 * in request for further processing
	 * 
	 * @param ex
	 * @param request
	 */
	@ExceptionHandler(value = { ApplicationException.class })
	protected void handleApplicationException(ApplicationException ex, WebRequest request) {

		logger.error(
				"ApplicationException caught in Class : ExceptionLogger, Method : handleApplicationException(), at "
						+ LocalDateTime.now());
		logger.error("Exception message : " + ex.getMessage());
		logger.error("Exception stack : ", ex);

		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_APPLICATION,
				RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, ex.getMessage(), RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	/**
	 * 
	 * captures all RuntimeException, logs them and populates exception messages in
	 * request for further processing
	 * 
	 * @param ex
	 * @param request
	 */
	@ExceptionHandler(value = { RuntimeException.class })
	protected void handleRuntimeException(RuntimeException ex, WebRequest request, HttpServletRequest httpRequest) {

		logger.error("RuntimeException caught in Class : ExceptionLogger, Method : handleRuntimeException(), at "
				+ LocalDateTime.now());
		logger.error("Exception message : " + ex.getMessage());
		logger.error("Exception stack : ", ex);

		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_RUNTIME, RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, Constant.ERROR_MESSAGE_INTERNAL_SERVER_ERROR, RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	/**
	 * captures all Exception, logs them and populates exception messages in request
	 * for further processing
	 * 
	 * @param ex
	 * @param request
	 * @param httpRequest
	 * @throws Exception
	 */
	@ExceptionHandler(value = { Exception.class })
	protected void handleException(Exception ex, WebRequest request, HttpServletRequest httpRequest) throws Exception {

		Exception rootException = (Exception) ExceptionUtils.getRootCause(ex);

		logger.error(
				"Exception caught in Class : ExceptionLogger, Method : handleException(), at " + LocalDateTime.now());
		logger.error("Exception message : " + ex.getMessage());

		// Log root exception, if any
		if (!Objects.isNull(rootException)) {
			logger.error("Exception root cause : " + rootException.getMessage());
			logger.error("Exception root stack : ", rootException);
		}

		logger.error("Exception whole stack : ", ex);

		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_EXCEPTION,
				RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, Constant.ERROR_MESSAGE_INTERNAL_SERVER_ERROR, RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}
}
