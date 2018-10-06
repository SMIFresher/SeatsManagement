package com.seatmanagement.exception;

public class SpringMVCStandardException extends RuntimeException {

	private static final long serialVersionUID = 3756796527310679088L;

	private String errorConstant;

	public SpringMVCStandardException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public SpringMVCStandardException(final String msg, final Throwable cause) {
		super(msg, cause);
		this.errorConstant = msg;
	}

	public String getErrorConstant() {
		return errorConstant;
	}

	public void setErrorConstant(String errorConstant) {
		this.errorConstant = errorConstant;
	}

}
