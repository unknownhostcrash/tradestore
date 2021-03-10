package com.deutsche.exceptions;

public class VersionNotValid extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4990044453255381160L;

	public VersionNotValid() {
		super();
	}
	
	public VersionNotValid(String message) {
		super(message);
	}
}
