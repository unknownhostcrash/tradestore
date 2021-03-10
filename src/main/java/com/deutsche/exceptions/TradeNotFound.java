package com.deutsche.exceptions;

public class TradeNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -337735844372039219L;

	public TradeNotFound() {
		super();
	}
	
	public TradeNotFound(String message) {
		super(message);
	}
}
