package br.com.Calculadora.Service;

import java.math.BigInteger;

import br.com.Calculadora.Form.DiluicaoConfiguracaoAtualizarForm;
import br.com.Calculadora.Form.DiluicaoConfiguracaoForm;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.DiluicaoConfiguracaoRepository;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.Repository.ViaAdministracaoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.DiluicaoConfiguracaoPK;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;
import br.com.Calculadora.orm.ViaAdministracao;

public class OperacoesService {

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

	public DiluicaoConfiguracao diluicaoFormToDiluicaoConfiguracao(DiluicaoConfiguracaoForm diluicaoConfiguracaoForm,
			Medicamento medicamento, ViaAdministracao viaAdministracao) {
		DiluicaoConfiguracao diluicaoConfiguracao = new DiluicaoConfiguracao();
		diluicaoConfiguracao.setDiluicaoConfiguracaoPK(medicamento, viaAdministracao, diluicaoConfiguracaoForm.getSequencia());
		//diluicaoConfiguracao.setSequencia(diluicaoConfiguracaoForm.getSequencia());
		//diluicaoConfiguracao.setMedicamento(medicamento);
		//diluicaoConfiguracao.setViaAdministracao(viaAdministracao);
		diluicaoConfiguracao.setQuantidadeAspirada(diluicaoConfiguracaoForm.getQuantidadeAspirada());
		diluicaoConfiguracao.setQuantidadeAdicionada(diluicaoConfiguracaoForm.getQuantidadeAdicionada());
		diluicaoConfiguracao.setConcentracao(diluicaoConfiguracaoForm.getConcentracao());
		diluicaoConfiguracao.setModoPreparo(diluicaoConfiguracaoForm.getModoPreparo());
		diluicaoConfiguracao.setDiluente(diluicaoConfiguracaoForm.getDiluente());
		return diluicaoConfiguracao;
	}

	public DiluicaoConfiguracao diluicaoAtualizarFormToDiluicao(BigInteger medicamentoId, BigInteger viaAdministracaoId,
			BigInteger sequencia, DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoAtualizarForm,
			MedicamentoRepository medicamentoRepository, ViaAdministracaoRepository viaAdministracaoRepository,
			DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository) {
		Medicamento medicamento = medicamentoRepository.getById(medicamentoId);
		ViaAdministracao viaAdministracao = viaAdministracaoRepository.getById(viaAdministracaoId);
		DiluicaoConfiguracaoPK diluicaoConfiguracaoPK = new DiluicaoConfiguracaoPK(medicamento, viaAdministracao,
				sequencia);
		
		DiluicaoConfiguracao diluicaoConfiguracao = diluicaoConfiguracaoRepository.getById(diluicaoConfiguracaoPK);
		//diluicaoConfiguracao.setSequencia(diluicaoConfiguracaoForm.getSequencia());
		//diluicaoConfiguracao.setMedicamento(medicamento);
		//diluicaoConfiguracao.setViaAdministracao(viaAdministracao);
		diluicaoConfiguracao.setQuantidadeAspirada(diluicaoConfiguracaoAtualizarForm.getQuantidadeAspirada());
		diluicaoConfiguracao.setQuantidadeAdicionada(diluicaoConfiguracaoAtualizarForm.getQuantidadeAdicionada());
		diluicaoConfiguracao.setConcentracao(diluicaoConfiguracaoAtualizarForm.getConcentracao());
		diluicaoConfiguracao.setModoPreparo(diluicaoConfiguracaoAtualizarForm.getModoPreparo());
		diluicaoConfiguracao.setDiluente(diluicaoConfiguracaoAtualizarForm.getDiluente());
		return diluicaoConfiguracao;
	}
}
