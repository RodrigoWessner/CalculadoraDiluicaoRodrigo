package br.com.Calculadora.Dto;

import java.math.BigInteger;

import br.com.Calculadora.orm.GrupoMedicamento;

public class GrupoMedicamentoDto {

	private BigInteger id;
	private String nome;

	public GrupoMedicamentoDto(GrupoMedicamento grupoMedicamento) {
		this.id = grupoMedicamento.getId();
		this.nome = grupoMedicamento.getNome();
	}
	
	public BigInteger getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	

}
