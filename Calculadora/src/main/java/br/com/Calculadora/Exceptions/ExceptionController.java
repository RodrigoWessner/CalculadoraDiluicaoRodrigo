package br.com.Calculadora.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public DefaultErrorDto resourceNotFoundException(RecordNotFoundException ex, WebRequest request) {
		DefaultErrorDto message = new DefaultErrorDto(ex.getMessage());
		
		/*
		 * ErrorMessage message = new ErrorMessage( HttpStatus.NOT_FOUND.value(), new
		 * Date(), ex.getMessage(), request.getDescription(false));
		 */

		return message;
	}
}
