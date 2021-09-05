package br.com.Calculadora.Form;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;

public class MedicamentoForm {
	@NotBlank
	@NotNull
	@NotEmpty
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id")
	private GrupoMedicamento grupoMedicamento;

	@ManyToOne
	@JoinColumn(name = "id")
	private Laboratorio laboratorio;

	private BigDecimal quantidadeApresentacao;
	private BigDecimal concentracaoInicial;
	private String infoSobra;
	private String infoObservacao;
	private String infoTempoAdministracao;
	private String unidadeMedida;
	private String embalagemApresentada;

	public MedicamentoForm(String nome, GrupoMedicamento grupoMedicamento, Laboratorio laboratorio,
			BigDecimal quantidadeApresentacao, BigDecimal concentracaoInicial, String infoSobra, String infoObservacao,
			String infoTempoAdministracao, String unidadeMedida, String embalagemApresentada) {
		this.nome = nome;
		this.grupoMedicamento = grupoMedicamento;
		this.laboratorio = laboratorio;
		this.quantidadeApresentacao = quantidadeApresentacao;
		this.concentracaoInicial = concentracaoInicial;
		this.infoSobra = infoSobra;
		this.infoObservacao = infoObservacao;
		this.infoTempoAdministracao = infoTempoAdministracao;
		this.unidadeMedida = unidadeMedida;
		this.embalagemApresentada = embalagemApresentada;
	}

	public Medicamento converter() {
		return (new Medicamento(nome, grupoMedicamento, laboratorio, quantidadeApresentacao, concentracaoInicial,
				infoSobra, infoObservacao, infoTempoAdministracao, unidadeMedida, embalagemApresentada));

	}

	public Medicamento atualizar(BigInteger id, MedicamentoRepository medicamentoRepository) {
		Medicamento medicamento = medicamentoRepository.getOne(id);
		medicamento.setNome(nome);
		medicamento.setGrupoMedicamento(grupoMedicamento);
		medicamento.setLaboratorio(laboratorio);
		medicamento.setQuantidadeApresentacao(quantidadeApresentacao);
		medicamento.setConcentracaoInicial(concentracaoInicial);
		medicamento.setInfoSobra(infoSobra);
		medicamento.setInfoObservacao(infoObservacao);
		medicamento.setInfoTempoAdministracao(infoTempoAdministracao);
		medicamento.setUnidadeMedida(unidadeMedida);
		medicamento.setEmbalagemApresentada(embalagemApresentada);
		return medicamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GrupoMedicamento getGrupoMedicamento() {
		return grupoMedicamento;
	}

	public void setGrupoMedicamento(GrupoMedicamento grupoMedicamento) {
		this.grupoMedicamento = grupoMedicamento;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
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
