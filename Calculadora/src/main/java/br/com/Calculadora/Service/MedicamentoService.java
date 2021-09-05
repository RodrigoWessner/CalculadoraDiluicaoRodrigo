package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.GrupoMedicamentoDto;
import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.orm.Medicamento;

@Service
public class MedicamentoService {

	MedicamentoRepository medicamentoRepository;

	@Autowired
	public MedicamentoService(MedicamentoRepository medicamentoRepository) {
		this.medicamentoRepository = medicamentoRepository;
	}

	// Cruds

	public Iterable<Medicamento> lista() {
		Iterable<Medicamento> medicamento = medicamentoRepository.findAll();
		return (medicamento);
	}

	public ResponseEntity<MedicamentoDto> criar(@Valid MedicamentoForm medicamentoForm,
			UriComponentsBuilder uriBuilder) {
		Medicamento medicamento = medicamentoForm.converter();
		medicamentoRepository.save(medicamento);

		URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(medicamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new MedicamentoDto(medicamento));
	}

	public ResponseEntity<MedicamentoDto> remover(BigInteger id) {
		medicamentoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<MedicamentoDto> atualizar(BigInteger id, MedicamentoForm medicamentoForm){
		Medicamento medicamento = medicamentoForm.atualizar(id, medicamentoRepository);
		return(ResponseEntity.ok(new MedicamentoDto(medicamento)));
	}
}
