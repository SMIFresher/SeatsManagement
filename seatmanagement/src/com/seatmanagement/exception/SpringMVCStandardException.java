package com.seatmanagement.exception;

public class SpringMVCStandardException extends RuntimeException {

	private static final long serialVersionUID = 3756796527310679088L;

	public SpringMVCStandardException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public SpringMVCStandardException(final String msg, final Throwable cause) {
		super(msg, cause);
	}

}
