package com.seatmanagement.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
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
	 * captures all BusinessException, logs them and populates exception
	 * messages in request for further processing
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
		
		Exception rootException = (Exception) ExceptionUtils.getRootCause(ex);

		logger.error("BusinessException caught in Class : ExceptionLogger, Method : handleBusinessException(), at "
				+ LocalDateTime.now());
		logger.error("Exception message : " + exceptionMessage);
		
		// Log root exception, if any
		if (!Objects.isNull(rootException)) {
			logger.error("Exception root cause : " + rootException.getMessage());
			logger.error("Exception root stack : ", rootException);
		}
		
		logger.error("Exception stack : ", ex);

		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_BUSINESS,
				RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, exceptionMessage.toString(), RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	/**
	 * 
	 * captures all ApplicationException, logs them and populates exception
	 * messages in request for further processing
	 * 
	 * @param ex
	 * @param request
	 */
	@ExceptionHandler(value = { ApplicationException.class })
	protected void handleApplicationException(ApplicationException ex, WebRequest request) {
		
		Exception rootException = (Exception) ExceptionUtils.getRootCause(ex);
		
		logger.error("ApplicationException caught in Class : ExceptionLogger, Method : handleApplicationException(), at "
				+ LocalDateTime.now());
		logger.error("Exception message : " + ex.getMessage());
		
		// Log root exception, if any
		if (!Objects.isNull(rootException)) {
			logger.error("Exception root cause : " + rootException.getMessage());
			logger.error("Exception root stack : ", rootException);
		}
		
		logger.error("Exception whole stack : ", ex);

		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_APPLICATION,
				RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, ex.getMessage(), RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	/**
	 * 
	 * captures all RuntimeException, logs them and populates exception messages
	 * in request for further processing
	 * 
	 * @param ex
	 * @param request
	 */
	@ExceptionHandler(value = { RuntimeException.class })
	protected void handleRuntimeException(RuntimeException ex, WebRequest request, HttpServletRequest httpRequest) {

		Exception rootException = (Exception) ExceptionUtils.getRootCause(ex);
		
		logger.error("RuntimeException caught in Class : ExceptionLogger, Method : handleRuntimeException(), at "
				+ LocalDateTime.now());
		logger.error("Exception message : " + ex.getMessage());
		
		// Log root exception, if any
		if (!Objects.isNull(rootException)) {
			logger.error("Exception root cause : " + rootException.getMessage());
			logger.error("Exception root stack : ", rootException);
		}
		
		logger.error("Exception whole stack : ", ex);

		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_RUNTIME, RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(Constant.EXCEPTION_MESSAGE, Constant.ERROR_MESSAGE_INTERNAL_SERVER_ERROR,
				RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	/**
	 * captures all Exception, logs them and populates exception messages in
	 * request for further processing
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
		request.setAttribute(Constant.EXCEPTION_MESSAGE, Constant.ERROR_MESSAGE_INTERNAL_SERVER_ERROR,
				RequestAttributes.SCOPE_REQUEST);

		throw ex;
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// same implementation as in ResponseEntityExceptionHandler
		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);

			// store header in requestscope to send with generated response
			request.setAttribute(Constant.HTTP_HEADER, headers, RequestAttributes.SCOPE_REQUEST);
		}

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// same implementation as in ResponseEntityExceptionHandler
		List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
		if (!CollectionUtils.isEmpty(mediaTypes)) {
			headers.setAccept(mediaTypes);

			// store header in requestscope to send with generated response
			request.setAttribute(Constant.HTTP_HEADER, headers, RequestAttributes.SCOPE_REQUEST);
		}

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, request);

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		// log the exception and set appropriate request attributes
		logAndSetAttributesForStandardSpringMVCExceptions(ex, webRequest);

		// same implementation as in ResponseEntityExceptionHandler
		if (webRequest instanceof ServletWebRequest) {
			ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
			HttpServletRequest request = servletWebRequest.getRequest();
			HttpServletResponse response = servletWebRequest.getResponse();
			if (response != null && response.isCommitted()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Async timeout for " + request.getMethod() + " [" + request.getRequestURI() + "]");
				}
			}
		}

		// Handle exception through custom our Exceptionhandler instead of
		// ResponseEntityExceptionHandler
		// as dynamic response needs top be generated
		if (true) {
			throw new SpringMVCStandardException(ex.getMessage());
		}

		return handleExceptionInternal(ex, null, headers, status, webRequest);
	}

	private void logAndSetAttributesForStandardSpringMVCExceptions(Exception e, WebRequest request) {

		logger.error("StandardSpringMVCException caught in Class : ExceptionLogger, at " + LocalDateTime.now());
		logger.error("Exception message : " + e.getMessage());
		logger.error("Exception stack : ", e);

		// add EXCEPTION_TYPE attribute with value 'STANDARD_SPRING_MVC_EXCEPTION'
		// in request for further processing down the chain
		request.setAttribute(Constant.EXCEPTION_TYPE, Constant.EXCEPTION_TYPE_STANDARD_SPRING_MVC_EXCEPTION,
				RequestAttributes.SCOPE_REQUEST);

		// add exceptionMessage in request for further processing down the chain
		request.setAttribute(Constant.EXCEPTION_MESSAGE, Constant.ERROR_MESSAGE_INTERNAL_SERVER_ERROR, RequestAttributes.SCOPE_REQUEST);
	}
}
