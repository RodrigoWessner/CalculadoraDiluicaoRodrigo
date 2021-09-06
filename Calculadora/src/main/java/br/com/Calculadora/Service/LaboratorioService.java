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
import br.com.Calculadora.Dto.LaboatorioDto;
import br.com.Calculadora.Form.LaboratorioForm;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.orm.Laboratorio;

@Service
public class LaboratorioService {

	LaboratorioRepository laboratorioRepository;

	@Autowired
	public LaboratorioService(LaboratorioRepository laboratorioRepository) {
		this.laboratorioRepository = laboratorioRepository;
	}

	// metodos
	public Iterable<Laboratorio> lista() {
		Iterable<Laboratorio> laboratorio = laboratorioRepository.findAll();
		return laboratorio;
	}

	public ResponseEntity<LaboatorioDto> criar(LaboratorioForm laboratorioForm, BindingResult result,
			UriComponentsBuilder uriBuilder) {
		if (result.hasErrors()) {
			throw new RuntimeException();
		} else {
			Laboratorio laboratorio = laboratorioForm.converter();
			laboratorioRepository.save(laboratorio);

			URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(laboratorio.getId()).toUri();
			return ResponseEntity.created(uri).body(new LaboatorioDto(laboratorio));
		}
	}

	public ResponseEntity<LaboatorioDto> atualizar(BigInteger id, LaboratorioForm laboratorioForm,
			BindingResult result) {
		if (result.hasErrors() || !laboratorioRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			Laboratorio laboratorio = laboratorioForm.atualizar(id, laboratorioRepository);
			return ResponseEntity.ok(new LaboatorioDto(laboratorio));
		}
	}

	public ResponseEntity<LaboatorioDto> remover(BigInteger id) {
		if (!laboratorioRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			laboratorioRepository.deleteById(id);
			return (ResponseEntity.ok().build());
		}
	}
}
