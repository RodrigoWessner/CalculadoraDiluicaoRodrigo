package br.com.Calculadora.Form;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DiluicaoConfiguracaoForm {
	private BigInteger sequencia;
	private BigInteger medicamentoId;
	private BigInteger viaAdministracaoId;
	private BigDecimal quantidadeAspirada;
	private BigDecimal quantidadeAdicionada;
	private BigDecimal concentracao;
	private String modoPreparo;
	private String diluente;

	public DiluicaoConfiguracaoForm(BigInteger sequencia, BigInteger medicamentoId, BigInteger viaAdministracaoId,
			BigDecimal quantidadeAspirada, BigDecimal quantidadeAdicionada, BigDecimal concentracao, String modoPreparo,
			String diluente) {
		this.sequencia = sequencia;
		this.medicamentoId = medicamentoId;
		this.viaAdministracaoId = viaAdministracaoId;
		this.quantidadeAspirada = quantidadeAspirada;
		this.quantidadeAdicionada = quantidadeAdicionada;
		this.concentracao = concentracao;
		this.modoPreparo = modoPreparo;
		this.diluente = diluente;
	}

	public BigInteger getSequencia() {
		return sequencia;
	}

	public void setSequencia(BigInteger sequencia) {
		this.sequencia = sequencia;
	}

	public BigInteger getMedicamentoId() {
		return medicamentoId;
	}

	public void setMedicamentoId(BigInteger medicamentoId) {
		this.medicamentoId = medicamentoId;
	}

	public BigInteger getViaAdministracaoId() {
		return viaAdministracaoId;
	}

	public void setViaAdministracaoId(BigInteger viaAdministracaoId) {
		this.viaAdministracaoId = viaAdministracaoId;
	}

	public BigDecimal getQuantidadeAspirada() {
		return quantidadeAspirada;
	}

	public void setQuantidadeAspirada(BigDecimal quantidadeAspirada) {
		this.quantidadeAspirada = quantidadeAspirada;
	}

	public BigDecimal getQuantidadeAdicionada() {
		return quantidadeAdicionada;
	}

	public void setQuantidadeAdicionada(BigDecimal quantidadeAdicionada) {
		this.quantidadeAdicionada = quantidadeAdicionada;
	}

	public BigDecimal getConcentracao() {
		return concentracao;
	}

	public void setConcentracao(BigDecimal concentracao) {
		this.concentracao = concentracao;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public String getDiluente() {
		return diluente;
	}

	public void setDiluente(String diluente) {
		this.diluente = diluente;
	}

}
