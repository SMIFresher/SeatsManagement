package com.seatmanagement.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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

@ControllerAdvice
public class ExceptionLogger extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);

	@ExceptionHandler(value = { BusinessException.class })
	protected void handleBusinessException(BusinessException ex, WebRequest request) throws BusinessException {
		
		Boolean isValidationException = Boolean.FALSE;
		Errors errors = ex.getValidationErrors();
		StringBuilder exceptionMessage = null;
		
		if(!Objects.isNull(errors)) {
			isValidationException = Boolean.TRUE;
		}
		
		if(isValidationException) {
			List<FieldError> fieldErrors = errors.getFieldErrors();
			if(!Objects.isNull(fieldErrors) && !fieldErrors.isEmpty()) {
				exceptionMessage = new StringBuilder();
				for(FieldError fieldError : fieldErrors) {
					if(exceptionMessage.toString().isEmpty()) {
						exceptionMessage = exceptionMessage.append(fieldError.getDefaultMessage());
					}else {
						exceptionMessage = exceptionMessage.append(" ;" + fieldError.getDefaultMessage());
					}
				}
			}
		}else {
			exceptionMessage = new StringBuilder(ex.getMessage());
		}

		logger.error("BusinessException caught in Class : ExceptionLogger, Method : handleBusinessException(), at " + LocalDateTime.now());
		logger.error("Exception message : " + exceptionMessage);
		logger.error("Exception stack : ", ex);
		
		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_BUSINESS, RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, exceptionMessage.toString(), RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	@ExceptionHandler(value = { ApplicationException.class })
	protected void handleApplicationException(ApplicationException ex, WebRequest request) {

		logger.error("ApplicationException caught in Class : ExceptionLogger, Method : handleApplicationException(), at " + LocalDateTime.now());
		logger.error("Exception message : "+ ex.getMessage());
		logger.error("Exception stack : ", ex);
		
		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_APPLICATION, RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, ex.getMessage(), RequestAttributes.SCOPE_REQUEST);

		
		throw ex;
	}
	
	@ExceptionHandler(value = { RuntimeException.class })
	protected void handleRuntimeException(RuntimeException ex, WebRequest request, HttpServletRequest httpRequest) {

		logger.error("RuntimeException caught in Class : ExceptionLogger, Method : handleRuntimeException(), at " + LocalDateTime.now());
		logger.error("Exception message : "+ ex.getMessage());
		logger.error("Exception stack : ", ex);
		
		String ajaxHeader = httpRequest.getHeader("RequestType");
		
		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_RUNTIME, RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

}
