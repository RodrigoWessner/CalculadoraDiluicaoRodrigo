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
		return message;
	}

	@ExceptionHandler(DuplicateValueException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public DefaultErrorDto dupicatedValueException(DuplicateValueException ex, WebRequest request) {
		DefaultErrorDto message = new DefaultErrorDto(ex.getMessage());
		return message;
	}
}
