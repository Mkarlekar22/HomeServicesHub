package com.app.exception;

public class InvalidCredentialException extends RuntimeException{

	public InvalidCredentialException(String msg) {
		
		super(msg);
	}
}
