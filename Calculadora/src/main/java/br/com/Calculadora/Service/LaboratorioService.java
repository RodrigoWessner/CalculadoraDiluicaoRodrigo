package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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
	public ResponseEntity<List<LaboatorioDto>> lista() {
		List<Laboratorio> laboratorio = laboratorioRepository.findAll();
		List<LaboatorioDto> laboratorioDtoList = new ArrayList<LaboatorioDto>();
		laboratorio.forEach(lab -> {
			laboratorioDtoList.add(new LaboatorioDto(lab));
		});
		return ResponseEntity.ok(laboratorioDtoList);
	}

	public ResponseEntity<LaboatorioDto> lista(BigInteger id) {
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
		LaboatorioDto laboratorioDto = new LaboatorioDto(laboratorio.get());
		return ResponseEntity.ok(laboratorioDto);
	}
	
	public ResponseEntity<LaboatorioDto> lista(String nome) {
		Laboratorio laboratorio = laboratorioRepository.findByNome(nome);
		LaboatorioDto laboratorioDto = new LaboatorioDto(laboratorio);
		return ResponseEntity.ok(laboratorioDto);
	}

	public ResponseEntity<LaboatorioDto> criar(LaboratorioForm laboratorioForm, UriComponentsBuilder uriBuilder) {
		try {
			Laboratorio laboratorio = new Laboratorio(laboratorioForm.getNome());
			laboratorioRepository.save(laboratorio);

			URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(laboratorio.getId()).toUri();
			return ResponseEntity.created(uri).body(new LaboatorioDto(laboratorio));
		} catch (RuntimeException exception) {
			throw exception;
		}
	}

	public ResponseEntity<LaboatorioDto> atualizar(BigInteger id, LaboratorioForm laboratorioForm) {
		if (!laboratorioRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			try {
				Laboratorio laboratorio = laboratorioRepository.getById(id);
				laboratorio.setNome(laboratorioForm.getNome());
				return ResponseEntity.ok(new LaboatorioDto(laboratorio));
			} catch (RuntimeException exception) {
				throw exception;
			}
		}
	}

	public ResponseEntity<LaboatorioDto> remover(BigInteger id) {
		if (!laboratorioRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
			LaboatorioDto laboratorioDto = new LaboatorioDto(laboratorio.get());
			laboratorioRepository.deleteById(id);
			return (ResponseEntity.ok(laboratorioDto));
		}
	}
}
