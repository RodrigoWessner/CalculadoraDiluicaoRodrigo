package br.com.Calculadora.Service;

import org.springframework.stereotype.Service;

import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;

@Service
public class OperacoesService {

	public Medicamento medicamentoFormToMedicamento(MedicamentoForm medicamentoForm, Laboratorio laboratorio, GrupoMedicamento grupoMedicamento) {
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
}
