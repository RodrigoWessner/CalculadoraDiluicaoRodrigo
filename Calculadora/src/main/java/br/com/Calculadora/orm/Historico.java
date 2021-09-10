package br.com.Calculadora.orm;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	private BigInteger id;

	private String nomeUsuario;
	private String nomeMedicamento;
	private String quantidadeApresentada;
	private String quantidadePrescrita;
	private String viaAdministracao;
	private String resultadosJson;
	private Date dataCalculo;

	public BigInteger getId() {
		return id;
	}

	public Historico() {
	}

	public Historico(BigInteger id, String nomeUsuario, String nomeMedicamento, String quantidadeApresentada,
			String quantidadePrescrita, String viaAdministracao, String resultadosJson, Date dataCalculo) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.nomeMedicamento = nomeMedicamento;
		this.quantidadeApresentada = quantidadeApresentada;
		this.quantidadePrescrita = quantidadePrescrita;
		this.viaAdministracao = viaAdministracao;
		this.resultadosJson = resultadosJson;
		this.dataCalculo = dataCalculo;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}

	public String getQuantidadeApresentada() {
		return quantidadeApresentada;
	}

	public void setQuantidadeApresentada(String quantidadeApresentada) {
		this.quantidadeApresentada = quantidadeApresentada;
	}

	public String getQuantidadePrescrita() {
		return quantidadePrescrita;
	}

	public void setQuantidadePrescrita(String quantidadePrescrita) {
		this.quantidadePrescrita = quantidadePrescrita;
	}

	public String getViaAdministracao() {
		return viaAdministracao;
	}

	public void setViaAdministracao(String viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}

	public String getResultadosJson() {
		return resultadosJson;
	}

	public void setResultadosJson(String resultadosJson) {
		this.resultadosJson = resultadosJson;
	}

	public Date getDataCalculo() {
		return dataCalculo;
	}

	public void setDataCalculo(Date dataCalculo) {
		this.dataCalculo = dataCalculo;
	}

}
