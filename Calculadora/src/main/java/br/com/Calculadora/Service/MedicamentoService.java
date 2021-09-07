package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.GrupoMedicamentoDto;
import br.com.Calculadora.Dto.LaboatorioDto;
import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.Medicamento;

@Service
public class MedicamentoService {

	MedicamentoRepository medicamentoRepository;
	LaboratorioRepository laboratorioRepository;
	GrupoMedicamentoRepository grupoMedicamentoRepository;

	@Autowired
	public MedicamentoService(MedicamentoRepository medicamentoRepository, LaboratorioRepository laboratorioRepository,
			GrupoMedicamentoRepository grupoMedicamentoRepository) {
		this.medicamentoRepository = medicamentoRepository;
		this.laboratorioRepository = laboratorioRepository;
		this.grupoMedicamentoRepository = grupoMedicamentoRepository;
	}

	// Cruds

	public ResponseEntity<List<MedicamentoDto>> lista() {
		List<Medicamento> medicamento = medicamentoRepository.findAll();
		List<MedicamentoDto> MedicamentoList = new ArrayList<MedicamentoDto>();
		
		medicamento.forEach(med ->{
			MedicamentoList.add(new MedicamentoDto(med));
		});

		return ResponseEntity.ok(MedicamentoList);
	}

	public ResponseEntity<MedicamentoDto> criar(@Valid MedicamentoForm medicamentoForm, UriComponentsBuilder uriBuilder,
			BindingResult result) {
		if (result.hasErrors() || (grupoMedicamentoRepository.findById(medicamentoForm.getIdGrupoMedicamento()) == null)
				|| (laboratorioRepository.findById(medicamentoForm.getIdLaboratorio()) == null)) {
			throw new RuntimeException();
		} else {
			Medicamento medicamento = medicamentoForm.converter(laboratorioRepository, grupoMedicamentoRepository);
			medicamentoRepository.save(medicamento);

			URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(medicamento.getId()).toUri();
			return ResponseEntity.created(uri).body(new MedicamentoDto(medicamento));
		}
	}

	public ResponseEntity<MedicamentoDto> remover(BigInteger id) {
		if (!medicamentoRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
			MedicamentoDto medicamentoDto = new MedicamentoDto(medicamento.get());
			medicamentoRepository.deleteById(id);
			return (ResponseEntity.ok(medicamentoDto));
		}
	}

	public ResponseEntity<MedicamentoDto> atualizar(BigInteger id, MedicamentoForm medicamentoForm,
			BindingResult result) {
		if (result.hasErrors() || !medicamentoRepository.existsById(id) || (grupoMedicamentoRepository.findById(medicamentoForm.getIdGrupoMedicamento()) == null)
				|| (laboratorioRepository.findById(medicamentoForm.getIdLaboratorio()) == null)) {
			throw new RuntimeException();
		} else {
			Medicamento medicamento = medicamentoForm.atualizar(id, medicamentoRepository, laboratorioRepository,
					grupoMedicamentoRepository);
			return (ResponseEntity.ok(new MedicamentoDto(medicamento)));
		}
	}
}
