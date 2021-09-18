package br.com.Calculadora.Exceptions;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String mensagem) {
		super(mensagem);
	}
}
