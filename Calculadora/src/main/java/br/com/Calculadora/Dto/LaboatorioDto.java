package br.com.Calculadora.Dto;

import java.math.BigInteger;

import br.com.Calculadora.orm.Laboratorio;

public class LaboatorioDto {
	private BigInteger id;
	private String nome;

	public LaboatorioDto(Laboratorio laboratorio) {
		this.id = laboratorio.getId();
		this.nome = laboratorio.getNome();
	}

	public BigInteger getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	

}
