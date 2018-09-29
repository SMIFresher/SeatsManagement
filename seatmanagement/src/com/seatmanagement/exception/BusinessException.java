package com.seatmanagement.exception;

import org.springframework.validation.Errors;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 2597478625120579985L;

	private String errorConstant;

	private Errors validationErrors;

	public BusinessException(Errors validationErrors) {
		super();
		this.validationErrors = validationErrors;
	}

	public BusinessException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public BusinessException(final String msg, final Throwable cause) {
		super(msg, cause);
		this.errorConstant = msg;
	}

	public Errors getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(Errors validationErrors) {
		this.validationErrors = validationErrors;
	}

	/**
	 * @return the errorConstant
	 */
	public String getErrorConstant() {
		return errorConstant;
	}

	/**
	 * @param errorConstant
	 *            the errorConstant to set
	 */
	public void setErrorConstant(String errorConstant) {
		this.errorConstant = errorConstant;
	}
}
