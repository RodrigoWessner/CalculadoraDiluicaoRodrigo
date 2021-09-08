package br.com.Calculadora.Form;

import javax.validation.constraints.NotBlank;

public class GrupoMedicamentoForm {

    @NotBlank(message = "Nome não pode ser nulo nem vazio!")	
    private String nome;
	
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
