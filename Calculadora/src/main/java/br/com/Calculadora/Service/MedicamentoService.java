package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.GrupoMedicamentoDto;
import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
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

	public Iterable<Medicamento> lista() {
		Iterable<Medicamento> medicamento = medicamentoRepository.findAll();
		return (medicamento);
	}

	public ResponseEntity<MedicamentoDto> criar(@Valid MedicamentoForm medicamentoForm, UriComponentsBuilder uriBuilder,
			BindingResult result) {
		if (result.hasErrors() || (grupoMedicamentoRepository.findByNome(medicamentoForm.getGrupoMedicamento()) == null)
				|| (laboratorioRepository.findByNome(medicamentoForm.getNomeLaboratorio()) == null)) {
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
			medicamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}

	public ResponseEntity<MedicamentoDto> atualizar(BigInteger id, MedicamentoForm medicamentoForm,
			BindingResult result) {
		if (result.hasErrors() || !medicamentoRepository.existsById(id) || (grupoMedicamentoRepository.findByNome(medicamentoForm.getGrupoMedicamento()) == null)
				|| (laboratorioRepository.findByNome(medicamentoForm.getNomeLaboratorio()) == null)) {
			throw new RuntimeException();
		} else {
			Medicamento medicamento = medicamentoForm.atualizar(id, medicamentoRepository, laboratorioRepository,
					grupoMedicamentoRepository);
			return (ResponseEntity.ok(new MedicamentoDto(medicamento)));
		}
	}
}
