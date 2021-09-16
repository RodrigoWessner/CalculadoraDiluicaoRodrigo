package br.com.Calculadora.Dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.com.Calculadora.orm.Medicamento;

public class MedicamentoConfiguracaoDto {
	private BigInteger id;
	private String nome;
	private BigInteger idMedicamento;
	private BigInteger idLaboratorio;
	private BigDecimal quantidadeApresentacao;
	private BigDecimal concentracaoInicial;
	private String infoSobra;
	private String infoObservacao;
	private String infoTempoAdministracao;
	private String unidadeMedida;
	private String embalagemApresentada;
	private List<DiluicaoConfiguracaoDto> diluicaoConfiguracaoList;

	public MedicamentoConfiguracaoDto(Medicamento medicamento, List<DiluicaoConfiguracaoDto> diluicaoConfiguracaoDtos) {
		this.id = medicamento.getId();
		this.nome = medicamento.getNome();
		this.idMedicamento = medicamento.getGrupoMedicamento().getId();
		this.idLaboratorio = medicamento.getLaboratorio().getId();
		this.quantidadeApresentacao = medicamento.getQuantidadeApresentacao();
		this.concentracaoInicial = medicamento.getConcentracaoInicial();
		this.infoSobra = medicamento.getInfoSobra();
		this.infoObservacao = medicamento.getInfoObservacao();
		this.infoTempoAdministracao = medicamento.getInfoTempoAdministracao();
		this.unidadeMedida = medicamento.getUnidadeMedida();
		this.embalagemApresentada = medicamento.getEmbalagemApresentada();
		this.diluicaoConfiguracaoList = new ArrayList<DiluicaoConfiguracaoDto>();
		diluicaoConfiguracaoDtos.forEach(diluicao -> {
			this.diluicaoConfiguracaoList.add(diluicao);
		});
	}
	
	public MedicamentoConfiguracaoDto(Medicamento medicamento) {
		this.id = medicamento.getId();
		this.nome = medicamento.getNome();
		this.idMedicamento = medicamento.getGrupoMedicamento().getId();
		this.idLaboratorio = medicamento.getLaboratorio().getId();
		this.quantidadeApresentacao = medicamento.getQuantidadeApresentacao();
		this.concentracaoInicial = medicamento.getConcentracaoInicial();
		this.infoSobra = medicamento.getInfoSobra();
		this.infoObservacao = medicamento.getInfoObservacao();
		this.infoTempoAdministracao = medicamento.getInfoTempoAdministracao();
		this.unidadeMedida = medicamento.getUnidadeMedida();
		this.embalagemApresentada = medicamento.getEmbalagemApresentada();
	}

	public List<DiluicaoConfiguracaoDto> getDiluicaoConfiguracaoList() {
		return diluicaoConfiguracaoList;
	}

	public BigInteger getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigInteger getIdMedicamento() {
		return idMedicamento;
	}

	public BigInteger getIdLaboratorio() {
		return idLaboratorio;
	}

	public BigDecimal getQuantidadeApresentacao() {
		return quantidadeApresentacao;
	}

	public BigDecimal getConcentracaoInicial() {
		return concentracaoInicial;
	}

	public String getInfoSobra() {
		return infoSobra;
	}

	public String getInfoObservacao() {
		return infoObservacao;
	}

	public String getInfoTempoAdministracao() {
		return infoTempoAdministracao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public String getEmbalagemApresentada() {
		return embalagemApresentada;
	}

}
