package br.com.Calculadora.Service;

import java.math.BigInteger;

import br.com.Calculadora.Form.DiluicaoConfiguracaoAtualizarForm;
import br.com.Calculadora.Form.DiluicaoConfiguracaoAtualizarSemPKForm;
import br.com.Calculadora.Form.DiluicaoConfiguracaoForm;
import br.com.Calculadora.Form.MedicamentoConfiguracaoForm;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;
import br.com.Calculadora.orm.ViaAdministracao;

public class OperacoesService {
	public Medicamento medicamentoConfiguracaoFormToMedicamento(MedicamentoConfiguracaoForm medicamentoConfiguracaoForm,
			Laboratorio laboratorio, GrupoMedicamento grupoMedicamento) {
		Medicamento medicamento = new Medicamento();
		medicamento.setNome(medicamentoConfiguracaoForm.getNome());
		medicamento.setLaboratorio(laboratorio);
		medicamento.setGrupoMedicamento(grupoMedicamento);
		medicamento.setConcentracaoInicial(medicamentoConfiguracaoForm.getConcentracaoInicial());
		medicamento.setEmbalagemApresentada(medicamentoConfiguracaoForm.getEmbalagemApresentada());
		medicamento.setInfoObservacao(medicamentoConfiguracaoForm.getInfoObservacao());
		medicamento.setInfoSobra(medicamentoConfiguracaoForm.getInfoSobra());
		medicamento.setInfoTempoAdministracao(medicamentoConfiguracaoForm.getInfoTempoAdministracao());
		medicamento.setQuantidadeApresentacao(medicamentoConfiguracaoForm.getQuantidadeApresentacao());
		medicamento.setUnidadeMedida(medicamentoConfiguracaoForm.getUnidadeMedida());
		return medicamento;
	}

	public Medicamento medicamentoFormToMedicamento(MedicamentoForm medicamentoForm, Laboratorio laboratorio,
			GrupoMedicamento grupoMedicamento) {
		Medicamento medicamento = new Medicamento();
		medicamento.setNome(medicamentoForm.getNome());
		medicamento.setLaboratorio(laboratorio);
		medicamento.setGrupoMedicamento(grupoMedicamento);
		medicamento.setConcentracaoInicial(medicamentoForm.getConcentracaoInicial());
		medicamento.setEmbalagemApresentada(medicamentoForm.getEmbalagemApresentada());
		medicamento.setInfoObservacao(medicamentoForm.getInfoObservacao());
		medicamento.setInfoSobra(medicamentoForm.getInfoSobra());
		medicamento.setInfoTempoAdministracao(medicamentoForm.getInfoTempoAdministracao());
		medicamento.setQuantidadeApresentacao(medicamentoForm.getQuantidadeApresentacao());
		medicamento.setUnidadeMedida(medicamentoForm.getUnidadeMedida());
		return medicamento;
	}

	public Medicamento medicamentoFormToMedicamento(BigInteger id, MedicamentoForm medicamentoForm,
			MedicamentoRepository medicamentoRepository, GrupoMedicamentoRepository grupoMedicamentoRepository,
			LaboratorioRepository laboratorioRepository) {

		Medicamento medicamento = medicamentoRepository.getById(id);
		Laboratorio laboratorio = laboratorioRepository.getById(medicamentoForm.getIdLaboratorio());
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.getById(medicamentoForm.getIdGrupoMedicamento());

		medicamento.setNome(medicamentoForm.getNome());
		medicamento.setLaboratorio(laboratorio);
		medicamento.setGrupoMedicamento(grupoMedicamento);
		medicamento.setConcentracaoInicial(medicamentoForm.getConcentracaoInicial());
		medicamento.setEmbalagemApresentada(medicamentoForm.getEmbalagemApresentada());
		medicamento.setInfoObservacao(medicamentoForm.getInfoObservacao());
		medicamento.setInfoSobra(medicamentoForm.getInfoSobra());
		medicamento.setInfoTempoAdministracao(medicamentoForm.getInfoTempoAdministracao());
		medicamento.setQuantidadeApresentacao(medicamentoForm.getQuantidadeApresentacao());
		medicamento.setUnidadeMedida(medicamentoForm.getUnidadeMedida());
		return medicamento;
	}

	public Medicamento medicamentoFormToMedicamento(Medicamento medicamento, Laboratorio laboratorio,
			GrupoMedicamento grupoMedicamento, MedicamentoConfiguracaoForm medicamentoConfiguracaoForm) {
		medicamento.setNome(medicamentoConfiguracaoForm.getNome());
		medicamento.setLaboratorio(laboratorio);
		medicamento.setGrupoMedicamento(grupoMedicamento);
		medicamento.setConcentracaoInicial(medicamentoConfiguracaoForm.getConcentracaoInicial());
		medicamento.setEmbalagemApresentada(medicamentoConfiguracaoForm.getEmbalagemApresentada());
		medicamento.setInfoObservacao(medicamentoConfiguracaoForm.getInfoObservacao());
		medicamento.setInfoSobra(medicamentoConfiguracaoForm.getInfoSobra());
		medicamento.setInfoTempoAdministracao(medicamentoConfiguracaoForm.getInfoTempoAdministracao());
		medicamento.setQuantidadeApresentacao(medicamentoConfiguracaoForm.getQuantidadeApresentacao());
		medicamento.setUnidadeMedida(medicamentoConfiguracaoForm.getUnidadeMedida());
		return medicamento;
	}

	public DiluicaoConfiguracao diluicaoFormToDiluicaoConfiguracao(DiluicaoConfiguracaoForm diluicaoConfiguracaoForm,
			Medicamento medicamento, ViaAdministracao viaAdministracao) {
		DiluicaoConfiguracao diluicaoConfiguracao = new DiluicaoConfiguracao();
		diluicaoConfiguracao.setDiluicaoConfiguracaoPK(medicamento, viaAdministracao,
				diluicaoConfiguracaoForm.getSequencia());
		// diluicaoConfiguracao.setSequencia(diluicaoConfiguracaoForm.getSequencia());
		// diluicaoConfiguracao.setMedicamento(medicamento);
		// diluicaoConfiguracao.setViaAdministracao(viaAdministracao);
		diluicaoConfiguracao.setQuantidadeAspirada(diluicaoConfiguracaoForm.getQuantidadeAspirada());
		diluicaoConfiguracao.setQuantidadeAdicionada(diluicaoConfiguracaoForm.getQuantidadeAdicionada());
		diluicaoConfiguracao.setConcentracao(diluicaoConfiguracaoForm.getConcentracao());
		diluicaoConfiguracao.setModoPreparo(diluicaoConfiguracaoForm.getModoPreparo());
		diluicaoConfiguracao.setDiluente(diluicaoConfiguracaoForm.getDiluente());
		return diluicaoConfiguracao;
	}

	public DiluicaoConfiguracao diluicaoFormToDiluicaoConfiguracao(
			DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoForm, Medicamento medicamento,
			ViaAdministracao viaAdministracao) {
		DiluicaoConfiguracao diluicaoConfiguracao = new DiluicaoConfiguracao();
		diluicaoConfiguracao.setDiluicaoConfiguracaoPK(medicamento, viaAdministracao,
				diluicaoConfiguracaoForm.getSequencia());
		// diluicaoConfiguracao.setSequencia(diluicaoConfiguracaoForm.getSequencia());
		// diluicaoConfiguracao.setMedicamento(medicamento);
		// diluicaoConfiguracao.setViaAdministracao(viaAdministracao);
		diluicaoConfiguracao.setQuantidadeAspirada(diluicaoConfiguracaoForm.getQuantidadeAspirada());
		diluicaoConfiguracao.setQuantidadeAdicionada(diluicaoConfiguracaoForm.getQuantidadeAdicionada());
		diluicaoConfiguracao.setConcentracao(diluicaoConfiguracaoForm.getConcentracao());
		diluicaoConfiguracao.setModoPreparo(diluicaoConfiguracaoForm.getModoPreparo());
		diluicaoConfiguracao.setDiluente(diluicaoConfiguracaoForm.getDiluente());
		return diluicaoConfiguracao;
	}

	public DiluicaoConfiguracao diluicaoAtualizarFormToDiluicao(DiluicaoConfiguracao diluicaoConfiguracao,
			DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoAtualizarForm) {
		/*
		 * Medicamento medicamento = medicamentoRepository.getById(medicamentoId);
		 * ViaAdministracao viaAdministracao =
		 * viaAdministracaoRepository.getById(viaAdministracaoId);
		 * DiluicaoConfiguracaoPK diluicaoConfiguracaoPK = new
		 * DiluicaoConfiguracaoPK(medicamento, viaAdministracao, sequencia);
		 * 
		 * 
		 * DiluicaoConfiguracao diluicaoConfiguracao =
		 * diluicaoConfiguracaoRepository.getById(diluicaoConfiguracaoPK);
		 */
		// diluicaoConfiguracao.setSequencia(diluicaoConfiguracaoForm.getSequencia());
		// diluicaoConfiguracao.setMedicamento(medicamento);
		// diluicaoConfiguracao.setViaAdministracao(viaAdministracao);
		diluicaoConfiguracao.setQuantidadeAspirada(diluicaoConfiguracaoAtualizarForm.getQuantidadeAspirada());
		diluicaoConfiguracao.setQuantidadeAdicionada(diluicaoConfiguracaoAtualizarForm.getQuantidadeAdicionada());
		diluicaoConfiguracao.setConcentracao(diluicaoConfiguracaoAtualizarForm.getConcentracao());
		diluicaoConfiguracao.setModoPreparo(diluicaoConfiguracaoAtualizarForm.getModoPreparo());
		diluicaoConfiguracao.setDiluente(diluicaoConfiguracaoAtualizarForm.getDiluente());
		return diluicaoConfiguracao;

	}
	
	public DiluicaoConfiguracao diluicaoAtualizarSemPKFormToDiluicao(DiluicaoConfiguracao diluicaoConfiguracao,
			DiluicaoConfiguracaoAtualizarSemPKForm diluicaoConfiguracaoAtualizarSemPKForm) {
		diluicaoConfiguracao.setQuantidadeAspirada(diluicaoConfiguracaoAtualizarSemPKForm.getQuantidadeAspirada());
		diluicaoConfiguracao.setQuantidadeAdicionada(diluicaoConfiguracaoAtualizarSemPKForm.getQuantidadeAdicionada());
		diluicaoConfiguracao.setConcentracao(diluicaoConfiguracaoAtualizarSemPKForm.getConcentracao());
		diluicaoConfiguracao.setModoPreparo(diluicaoConfiguracaoAtualizarSemPKForm.getModoPreparo());
		diluicaoConfiguracao.setDiluente(diluicaoConfiguracaoAtualizarSemPKForm.getDiluente());
		return diluicaoConfiguracao;
	}
}
