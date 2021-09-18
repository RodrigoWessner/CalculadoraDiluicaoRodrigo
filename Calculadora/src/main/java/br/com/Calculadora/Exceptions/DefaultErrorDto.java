package br.com.Calculadora.Exceptions;

public class DefaultErrorDto {
	private String errorMessage;

	public DefaultErrorDto(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
