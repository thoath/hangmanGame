package com.hangman.exception;

/**
 * Custom exception for hangman
 * @author lucas.luz
 *
 */
public class HangmanException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	String message;

	public HangmanException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
