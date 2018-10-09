package com.seatmanagement.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -7834535542354400068L;

	public ApplicationException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public ApplicationException(final String msg, final Throwable cause) {
		super(msg, cause);
	}

}
