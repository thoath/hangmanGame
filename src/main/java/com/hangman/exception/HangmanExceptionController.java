package com.hangman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Custom controller for hangman exceptions
 * @author lucas.luz
 *
 */
@RestControllerAdvice
public class HangmanExceptionController {

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(HangmanException.class)
   public HangmanException exception(HangmanException exception) {
      return exception;
   }
	
}
