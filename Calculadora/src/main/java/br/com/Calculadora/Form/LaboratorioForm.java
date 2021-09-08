package br.com.Calculadora.Form;

import java.math.BigInteger;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.orm.Laboratorio;

public class LaboratorioForm {
	@NotBlank(message = "Nome não pode ser nulo nem vazio!")
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Laboratorio atualizar(BigInteger id, LaboratorioRepository laboratorioRepository) {
		Laboratorio laboratorio = laboratorioRepository.getOne(id); //option
		laboratorio.setNome(nome);
		return laboratorio;
	}
}
