package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.LaboatorioDto;
import br.com.Calculadora.Exceptions.RecordNotFoundException;
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

	public ResponseEntity<List<LaboatorioDto>> lista() {
		List<Laboratorio> laboratorio = laboratorioRepository.findAll();
		if (laboratorio.isEmpty()) {
			throw new RecordNotFoundException("Não foram encontrados Laboratorios");
		}
		List<LaboatorioDto> laboratorioDtoList = new ArrayList<LaboatorioDto>();
		laboratorio.forEach(lab -> {
			laboratorioDtoList.add(new LaboatorioDto(lab));
		});
		return new ResponseEntity<>(laboratorioDtoList, HttpStatus.OK);
	}

	public ResponseEntity<LaboatorioDto> lista(BigInteger id) {
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
		if (!laboratorio.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Laboratorio com o id = " + id);
		}
		return new ResponseEntity<>(new LaboatorioDto(laboratorio.get()), HttpStatus.OK);
	}

	public ResponseEntity<LaboatorioDto> lista(String nome) {
		Laboratorio laboratorio = laboratorioRepository.findByNome(nome);
		if (laboratorio == null) {
			throw new RecordNotFoundException("Não encontrado Laboratorio com nome = " + nome);
		}
		return new ResponseEntity<>(new LaboatorioDto(laboratorio), HttpStatus.OK);
	}

	public ResponseEntity<LaboatorioDto> criar(LaboratorioForm laboratorioForm, UriComponentsBuilder uriBuilder) {
		Laboratorio laboratorio = new Laboratorio(laboratorioForm.getNome());
		laboratorioRepository.save(laboratorio);
		URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(laboratorio.getId()).toUri();
		return ResponseEntity.created(uri).body(new LaboatorioDto(laboratorio));
	}

	public ResponseEntity<LaboatorioDto> atualizar(BigInteger id, LaboratorioForm laboratorioForm) {
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
		if (!laboratorio.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Laboratorio com o id = " + id);
		}
		laboratorio.get().setNome(laboratorioForm.getNome());
		return new ResponseEntity<>(new LaboatorioDto(laboratorio.get()), HttpStatus.OK);
	}

	public ResponseEntity<LaboatorioDto> remover(BigInteger id) {
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
		if (!laboratorio.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Laboratorio com o id = " + id);
		}
		LaboatorioDto laboratorioDto = new LaboatorioDto(laboratorio.get());
		laboratorioRepository.deleteById(id);
		return new ResponseEntity<>(laboratorioDto, HttpStatus.OK);
	}
}
