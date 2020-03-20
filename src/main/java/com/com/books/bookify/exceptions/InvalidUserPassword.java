package com.com.books.bookify.exceptions;

public class InvalidUserPassword extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserPassword(String s){
		super(s);
	}
}
