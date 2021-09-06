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
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;

public class MedicamentoForm {
	@NotBlank(message = "Nome não pode ser nulo nem vazio")
	private String nome;

	@NotBlank(message = "grupo não pode ser nulo nem vazio")
	private String nomeGrupoMedicamento;

	@NotBlank(message = "Laboratorio não pode ser nulo nem vazio")
	private String nomeLaboratorio;

	private BigDecimal quantidadeApresentacao;
	private BigDecimal concentracaoInicial;
	private String infoSobra;
	private String infoObservacao;
	private String infoTempoAdministracao;
	private String unidadeMedida;
	private String embalagemApresentada;

	public MedicamentoForm(String nome, String nomeGrupoMedicamento, String nomeLaboratorio,
			BigDecimal quantidadeApresentacao, BigDecimal concentracaoInicial, String infoSobra, String infoObservacao,
			String infoTempoAdministracao, String unidadeMedida, String embalagemApresentada) {
		this.nome = nome;
		this.nomeGrupoMedicamento = nomeGrupoMedicamento;
		this.nomeLaboratorio = nomeLaboratorio;
		this.quantidadeApresentacao = quantidadeApresentacao;
		this.concentracaoInicial = concentracaoInicial;
		this.infoSobra = infoSobra;
		this.infoObservacao = infoObservacao;
		this.infoTempoAdministracao = infoTempoAdministracao;
		this.unidadeMedida = unidadeMedida;
		this.embalagemApresentada = embalagemApresentada;
	}

	public Medicamento converter(LaboratorioRepository laboratorioRepository,
			GrupoMedicamentoRepository grupoMedicamentoRepository) {
		Laboratorio laboratorio = laboratorioRepository.findByNome(nomeLaboratorio);
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findByNome(nomeGrupoMedicamento);

		return (new Medicamento(nome, grupoMedicamento, laboratorio, quantidadeApresentacao, concentracaoInicial,
				infoSobra, infoObservacao, infoTempoAdministracao, unidadeMedida, embalagemApresentada));
	}

	public Medicamento atualizar(BigInteger id, MedicamentoRepository medicamentoRepository,
			LaboratorioRepository laboratorioRepository, GrupoMedicamentoRepository grupoMedicamentoRepository) {
		Medicamento medicamento = medicamentoRepository.getOne(id);
		Laboratorio laboratorio = laboratorioRepository.findByNome(nomeLaboratorio);
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findByNome(nomeGrupoMedicamento);

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

	public String getGrupoMedicamento() {
		return nomeGrupoMedicamento;
	}

	public void setGrupoMedicamento(String nomeGrupoMedicamento) {
		this.nomeGrupoMedicamento = nomeGrupoMedicamento;
	}

	public String getNomeLaboratorio() {
		return nomeLaboratorio;
	}

	public void setNomeLaboratorio(String nomeLaboratorio) {
		this.nomeLaboratorio = nomeLaboratorio;
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
