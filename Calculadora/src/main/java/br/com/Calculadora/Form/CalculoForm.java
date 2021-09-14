package br.com.Calculadora.Form;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CalculoForm {
	private BigInteger idMedicamento;
	private BigInteger IdViaAdministracao;
	private BigDecimal prescricao;
	private String nomeUsuario;

	public CalculoForm(BigInteger idMedicamento, BigInteger idViaAdministracao, BigDecimal prescricao,
			String nomeUsuario) {
		this.idMedicamento = idMedicamento;
		IdViaAdministracao = idViaAdministracao;
		this.prescricao = prescricao;
		this.nomeUsuario = nomeUsuario;
	}

	public BigInteger getIdMedicamento() {
		return idMedicamento;
	}

	public BigInteger getIdViaAdministracao() {
		return IdViaAdministracao;
	}

	public BigDecimal getPrescricao() {
		return prescricao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

}
