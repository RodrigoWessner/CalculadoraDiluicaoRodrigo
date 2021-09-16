package br.com.Calculadora.Form;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class MedicamentoConfiguracaoForm {
	private String nome;
	private BigInteger idGrupoMedicamento;
	private BigInteger idLaboratorio;
	private BigDecimal quantidadeApresentacao;
	private BigDecimal concentracaoInicial;
	private String infoSobra;
	private String infoObservacao;
	private String infoTempoAdministracao;
	private String unidadeMedida;
	private String embalagemApresentada;
	List<DiluicaoConfiguracaoAtualizarForm> diluicaoConfiguracaoList;

	// List<diluicaoConfiguracao>
	public MedicamentoConfiguracaoForm(String nome, BigInteger idGrupoMedicamento, BigInteger idLaboratorio,
			BigDecimal quantidadeApresentacao, BigDecimal concentracaoInicial, String infoSobra, String infoObservacao,
			String infoTempoAdministracao, String unidadeMedida, String embalagemApresentada,
			List<DiluicaoConfiguracaoAtualizarForm> diluicaoConfiguracaoForm) {
		this.nome = nome;
		this.idGrupoMedicamento = idGrupoMedicamento;
		this.idLaboratorio = idLaboratorio;
		this.quantidadeApresentacao = quantidadeApresentacao;
		this.concentracaoInicial = concentracaoInicial;
		this.infoSobra = infoSobra;
		this.infoObservacao = infoObservacao;
		this.infoTempoAdministracao = infoTempoAdministracao;
		this.unidadeMedida = unidadeMedida;
		this.embalagemApresentada = embalagemApresentada;
		diluicaoConfiguracaoForm.forEach(diluicao -> {
			this.diluicaoConfiguracaoList.add(diluicao);
		});
	}

	/*
	 * public MedicamentoConfiguracaoForm(String nome, BigInteger
	 * idGrupoMedicamento, BigInteger idLaboratorio, BigDecimal
	 * quantidadeApresentacao, BigDecimal concentracaoInicial, String infoSobra,
	 * String infoObservacao, String infoTempoAdministracao, String unidadeMedida,
	 * String embalagemApresentada) { this.nome = nome; this.idGrupoMedicamento =
	 * idGrupoMedicamento; this.idLaboratorio = idLaboratorio;
	 * this.quantidadeApresentacao = quantidadeApresentacao;
	 * this.concentracaoInicial = concentracaoInicial; this.infoSobra = infoSobra;
	 * this.infoObservacao = infoObservacao; this.infoTempoAdministracao =
	 * infoTempoAdministracao; this.unidadeMedida = unidadeMedida;
	 * this.embalagemApresentada = embalagemApresentada; }
	 */
	public MedicamentoConfiguracaoForm() {
		// TODO Auto-generated constructor stub
	}

	public List<DiluicaoConfiguracaoAtualizarForm> getDiluicaoConfiguracaoList() {
		return Collections.unmodifiableList(diluicaoConfiguracaoList);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigInteger getIdGrupoMedicamento() {
		return idGrupoMedicamento;
	}

	public void setIdGrupoMedicamento(BigInteger idGrupoMedicamento) {
		this.idGrupoMedicamento = idGrupoMedicamento;
	}

	public BigInteger getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(BigInteger idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public BigDecimal getQuantidadeApresentacao() {
		return quantidadeApresentacao;
	}

	public void setQuantidadeApresentacao(BigDecimal quantidadeApresentacao) {
		this.quantidadeApresentacao = quantidadeApresentacao;
	}

	public BigDecimal getConcentracaoInicial() {
		return concentracaoInicial;
	}

	public void setConcentracaoInicial(BigDecimal concentracaoInicial) {
		this.concentracaoInicial = concentracaoInicial;
	}

	public String getInfoSobra() {
		return infoSobra;
	}

	public void setInfoSobra(String infoSobra) {
		this.infoSobra = infoSobra;
	}

	public String getInfoObservacao() {
		return infoObservacao;
	}

	public void setInfoObservacao(String infoObservacao) {
		this.infoObservacao = infoObservacao;
	}

	public String getInfoTempoAdministracao() {
		return infoTempoAdministracao;
	}

	public void setInfoTempoAdministracao(String infoTempoAdministracao) {
		this.infoTempoAdministracao = infoTempoAdministracao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getEmbalagemApresentada() {
		return embalagemApresentada;
	}

	public void setEmbalagemApresentada(String embalagemApresentada) {
		this.embalagemApresentada = embalagemApresentada;
	}

}
