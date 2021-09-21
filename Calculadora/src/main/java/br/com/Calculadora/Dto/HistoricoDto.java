package br.com.Calculadora.Dto;

import java.math.BigInteger;
import java.sql.Date;

import br.com.Calculadora.orm.Historico;

public class HistoricoDto {
	private BigInteger id;
	private String nomeUsuario;
	private String nomeMedicamento;
	private String quantidadeApresentada;
	private String quantidadePrescrita;
	private String viaAdministracao;
	private String resultadosJson;
	private Date dataCalculo;

	public HistoricoDto() {
	}

	
	public HistoricoDto(String nomeUsuario, String nomeMedicamento, String quantidadeApresentada,
			String quantidadePrescrita, String viaAdministracao, String resultadosJson, Date dataCalculo) {
		this.nomeUsuario = nomeUsuario;
		this.nomeMedicamento = nomeMedicamento;
		this.quantidadeApresentada = quantidadeApresentada;
		this.quantidadePrescrita = quantidadePrescrita;
		this.viaAdministracao = viaAdministracao;
		this.resultadosJson = resultadosJson;
		this.dataCalculo = dataCalculo;
	}


	public HistoricoDto(Historico historico) {
		this.id = historico.getId();
		this.nomeUsuario = historico.getNomeUsuario();
		this.nomeMedicamento = historico.getNomeMedicamento();
		this.quantidadeApresentada = historico.getQuantidadeApresentacao();
		this.quantidadePrescrita = historico.getQuantidadePrescrita();
		this.viaAdministracao = historico.getViaAdministracao();
		this.resultadosJson = historico.getResultadosJson();
		this.dataCalculo = historico.getDataCalculo();
	}

	public BigInteger getId() {
		return id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public String getQuantidadeApresentada() {
		return quantidadeApresentada;
	}

	public String getQuantidadePrescrita() {
		return quantidadePrescrita;
	}

	public String getViaAdministracao() {
		return viaAdministracao;
	}

	public String getResultadosJson() {
		return resultadosJson;
	}

	public Date getDataCalculo() {
		return dataCalculo;
	}

}
