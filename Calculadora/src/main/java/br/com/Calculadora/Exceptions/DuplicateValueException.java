package br.com.Calculadora.Exceptions;

public class DuplicateValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateValueException(String mensagem) {
		super(mensagem);
	}
}
