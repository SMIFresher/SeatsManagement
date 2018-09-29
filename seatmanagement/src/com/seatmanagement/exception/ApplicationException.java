package com.seatmanagement.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -7834535542354400068L;

	private String errorConstant;

	public ApplicationException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public ApplicationException(final String msg, final Throwable cause) {
		super(msg, cause);
		this.errorConstant = msg;
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
