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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.GrupoMedicamentoDto;
import br.com.Calculadora.Form.GrupoMedicamentoForm;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.orm.GrupoMedicamento;

@Service
public class GrupoMedicamentoService {

	GrupoMedicamentoRepository grupoMedicamentoRepository;

	@Autowired
	public GrupoMedicamentoService(GrupoMedicamentoRepository grupoMedicamentoRepository) {
		this.grupoMedicamentoRepository = grupoMedicamentoRepository;
	}

	// Metodos
	public ResponseEntity<List<GrupoMedicamentoDto>> lista() {
		List<GrupoMedicamento> grupoMedicamento = grupoMedicamentoRepository.findAll();
		List<GrupoMedicamentoDto> grupoMedicamentoList = new ArrayList<GrupoMedicamentoDto>();

		grupoMedicamento.forEach(grupo -> {
			grupoMedicamentoList.add(new GrupoMedicamentoDto(grupo));
		});

		return ResponseEntity.ok(grupoMedicamentoList);
	}

	public ResponseEntity<GrupoMedicamentoDto> criar(@Valid GrupoMedicamentoForm grupoMedicamentoForm,
			UriComponentsBuilder uriBuilder) {// pega info do corpo
		try {
			GrupoMedicamento grupoMedicamento = new GrupoMedicamento(grupoMedicamentoForm.getNome());
			grupoMedicamentoRepository.save(grupoMedicamento);

			URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(grupoMedicamento.getId()).toUri();
			return ResponseEntity.created(uri).body(new GrupoMedicamentoDto(grupoMedicamento));
		} catch (RuntimeException exception) {
			throw exception;
		}
	}

	public ResponseEntity<GrupoMedicamentoDto> atualizar(BigInteger id, GrupoMedicamentoForm grupoMedicamentoForm) {
		if (!grupoMedicamentoRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			try {
				GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.getById(id);
				grupoMedicamento.setNome(grupoMedicamentoForm.getNome());
				return ResponseEntity.ok(new GrupoMedicamentoDto(grupoMedicamento));
			} catch (RuntimeException exception) {
				throw exception;
			}
		}
	}

	public ResponseEntity<GrupoMedicamentoDto> remover(BigInteger id) {
		if (!grupoMedicamentoRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			try {
				Optional<GrupoMedicamento> grupoMedicamento = grupoMedicamentoRepository.findById(id);
				GrupoMedicamentoDto grupoMedicamentoDto = new GrupoMedicamentoDto(grupoMedicamento.get());
				grupoMedicamentoRepository.deleteById(id);
				return (ResponseEntity.ok(grupoMedicamentoDto));
			} catch (RuntimeException exception) {
				throw exception;
			}
		}
	}
	/*
	 public static List<GrupoMedicamentoDto> converter(List<GrupoMedicamento> grupoMedicamento){
		return grupoMedicamento.stream().map(GrupoMedicamentoDto::new).collect(Collectors.toList());
	}*/
}
