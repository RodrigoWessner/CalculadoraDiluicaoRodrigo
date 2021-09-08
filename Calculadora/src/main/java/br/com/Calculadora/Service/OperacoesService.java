package br.com.Calculadora.Service;

import java.math.BigInteger;


import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;

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
}
