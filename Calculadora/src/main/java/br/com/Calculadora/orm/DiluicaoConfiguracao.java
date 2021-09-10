package br.com.Calculadora.orm;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class DiluicaoConfiguracao {

	@EmbeddedId
	private DiluicaoConfiguracaoPK diluicaoConfiguracaoPK;

	private BigDecimal quantidadeAspirada;
	private BigDecimal quantidadeAdicionada;
	private BigDecimal concentracao;
	private String modoPreparo;
	private String diluente;

	public DiluicaoConfiguracao(Medicamento medicamento, ViaAdministracao viaAdministracao, BigInteger sequencia,
			BigDecimal quantidadeAspirada, BigDecimal quantidadeAdicionada, BigDecimal concentracao, String modoPreparo,
			String diluente) {
		this.diluicaoConfiguracaoPK = new DiluicaoConfiguracaoPK(medicamento, viaAdministracao, sequencia);
		this.quantidadeAspirada = quantidadeAspirada;
		this.quantidadeAdicionada = quantidadeAdicionada;
		this.concentracao = concentracao;
		this.modoPreparo = modoPreparo;
		this.diluente = diluente;
	}

	public DiluicaoConfiguracao() {
	}

	public Medicamento getMedicamento() {
		return this.diluicaoConfiguracaoPK.getMedicamento();
	}

	public ViaAdministracao getViaAdministracao() {
		return this.diluicaoConfiguracaoPK.getViaAdministracao();
	}

	public BigInteger getSequencia() {
		return this.diluicaoConfiguracaoPK.getSequencia();
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
		diluente = diluente;
	}

}
