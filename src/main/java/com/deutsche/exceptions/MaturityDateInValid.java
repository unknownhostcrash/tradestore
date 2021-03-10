package com.deutsche.exceptions;

public class MaturityDateInValid extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 53260885473739274L;

	public MaturityDateInValid() {
		super();
	}
	
	public MaturityDateInValid(String message) {
		super(message);
	}
}
