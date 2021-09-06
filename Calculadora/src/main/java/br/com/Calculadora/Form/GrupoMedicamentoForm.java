package br.com.Calculadora.Form;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.orm.GrupoMedicamento;

public class GrupoMedicamentoForm {

    @NotBlank(message = "Nome não pode ser nulo nem vazio!")	
    private String nome;
	
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GrupoMedicamento converter() {
		return new GrupoMedicamento(nome);
	}

	public GrupoMedicamento atualizar(BigInteger id, GrupoMedicamentoRepository grupoMedicamentoRepository) {
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.getOne(id);
		grupoMedicamento.setNome(nome);
		return grupoMedicamento;
	}

}
