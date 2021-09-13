package br.com.Calculadora.Dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.DiluicaoConfiguracaoPK;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;

public class DiluicaoConfiguracaoDto {
	private BigInteger sequencia;
	private BigInteger medicamentoId;
	private BigInteger viaAdministracao;
	private BigDecimal quantidadeAspirada;
	private BigDecimal quantidadeAdicionada;
	private BigDecimal concentracao;
	private String modoPreparo;
	private String diluente;

	public DiluicaoConfiguracaoDto(DiluicaoConfiguracao diluicaoConfiguracao) {
		this.sequencia = diluicaoConfiguracao.getSequencia();
		this.medicamentoId = diluicaoConfiguracao.getMedicamento().getId();
		this.viaAdministracao = diluicaoConfiguracao.getViaAdministracao().getId();
		this.quantidadeAspirada = diluicaoConfiguracao.getQuantidadeAspirada();
		this.quantidadeAdicionada = diluicaoConfiguracao.getQuantidadeAdicionada();
		this.concentracao = diluicaoConfiguracao.getConcentracao();
		this.modoPreparo = diluicaoConfiguracao.getModoPreparo();
		this.diluente = diluicaoConfiguracao.getDiluente();
	}

	public BigInteger getSequencia() {
		return sequencia;
	}

	public BigInteger getMedicamentoId() {
		return medicamentoId;
	}

	public BigInteger getViaAdministracao() {
		return viaAdministracao;
	}

	public BigDecimal getQuantidadeAspirada() {
		return quantidadeAspirada;
	}

	public BigDecimal getQuantidadeAdicionada() {
		return quantidadeAdicionada;
	}

	public BigDecimal getConcentracao() {
		return concentracao;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public String getDiluente() {
		return diluente;
	}

}
