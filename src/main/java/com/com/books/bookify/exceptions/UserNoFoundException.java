package com.com.books.bookify.exceptions;

public class UserNoFoundException extends RuntimeException{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public UserNoFoundException(String id){
	   super(id);
   }
}
