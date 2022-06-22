package com.cts.eauction.exception;

public class EauctionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final com.cts.eauction.dto.Error error;

	public EauctionException(String message, com.cts.eauction.dto.Error error) {
		super(message);
		this.error = error;
	}

	/**
	 * @return the error
	 */
	public com.cts.eauction.dto.Error getError() {
		return error;
	}

}
