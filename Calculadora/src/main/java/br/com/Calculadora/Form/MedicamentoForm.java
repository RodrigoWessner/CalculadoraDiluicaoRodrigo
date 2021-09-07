package br.com.Calculadora.Form;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

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

	@NotNull
	private BigInteger idGrupoMedicamento;

	@NotNull
	private BigInteger idLaboratorio;

	private BigDecimal quantidadeApresentacao;
	private BigDecimal concentracaoInicial;
	private String infoSobra;
	private String infoObservacao;
	private String infoTempoAdministracao;
	private String unidadeMedida;
	private String embalagemApresentada;

	public MedicamentoForm(String nome, BigInteger idGrupoMedicamento, BigInteger idLaboratorio,
			BigDecimal quantidadeApresentacao, BigDecimal concentracaoInicial, String infoSobra, String infoObservacao,
			String infoTempoAdministracao, String unidadeMedida, String embalagemApresentada) {
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
	}

	public Medicamento converter(LaboratorioRepository laboratorioRepository,
			GrupoMedicamentoRepository grupoMedicamentoRepository) {
		
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(idLaboratorio);
		Optional<GrupoMedicamento> grupoMedicamento = grupoMedicamentoRepository.findById(idGrupoMedicamento);

		return (new Medicamento(nome, grupoMedicamento.get(), laboratorio.get(), quantidadeApresentacao, concentracaoInicial,
				infoSobra, infoObservacao, infoTempoAdministracao, unidadeMedida, embalagemApresentada));
	}

	public Medicamento atualizar(BigInteger id, MedicamentoRepository medicamentoRepository,
			LaboratorioRepository laboratorioRepository, GrupoMedicamentoRepository grupoMedicamentoRepository) {
		Medicamento medicamento = medicamentoRepository.getOne(id);
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(idLaboratorio);
		Optional<GrupoMedicamento> grupoMedicamento = grupoMedicamentoRepository.findById(idGrupoMedicamento);

		medicamento.setNome(nome);
		medicamento.setGrupoMedicamento(grupoMedicamento.get());
		medicamento.setLaboratorio(laboratorio.get());
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
