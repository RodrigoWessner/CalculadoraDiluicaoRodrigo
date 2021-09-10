package br.com.Calculadora.Dto;

import java.math.BigInteger;

import br.com.Calculadora.orm.ViaAdministracao;

public class ViaAdministracaoDTO {
	private BigInteger id;
	private String nome;

	public ViaAdministracaoDTO(ViaAdministracao viaAdministracao) {
		this.id = viaAdministracao.getId();
		this.nome = viaAdministracao.getNome();
	}

	public BigInteger getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	

}
