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

import br.com.Calculadora.Dto.GrupoMedicamentoDto;
import br.com.Calculadora.Exceptions.RecordNotFoundException;
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

	public ResponseEntity<List<GrupoMedicamentoDto>> lista() {
		Optional<List<GrupoMedicamento>> grupoMedicamento = Optional.ofNullable(grupoMedicamentoRepository.findAll());
		grupoMedicamento.orElseThrow(() -> new RecordNotFoundException("Não foram encontrados Grupos de Medicamentos"));
		List<GrupoMedicamentoDto> grupoMedicamentoList = new ArrayList<GrupoMedicamentoDto>();
		grupoMedicamento.get().forEach(grupo -> {
			grupoMedicamentoList.add(new GrupoMedicamentoDto(grupo));
		});
		return new ResponseEntity<>(grupoMedicamentoList, HttpStatus.OK);
	}

	public ResponseEntity<GrupoMedicamentoDto> lista(BigInteger id) {
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Grupo de Medicamento com id = " + id));
		;
		return new ResponseEntity<>(new GrupoMedicamentoDto(grupoMedicamento), HttpStatus.OK);
	}

	public ResponseEntity<GrupoMedicamentoDto> lista(String nome) {
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findByNome(nome).orElseThrow(
				() -> new RecordNotFoundException("Não encontrado Grupo de Medicamento com nome = " + nome));
		;
		return new ResponseEntity<>(new GrupoMedicamentoDto(grupoMedicamento), HttpStatus.OK);
	}

	public ResponseEntity<GrupoMedicamentoDto> criar(GrupoMedicamentoForm grupoMedicamentoForm,
			UriComponentsBuilder uriBuilder) {
		GrupoMedicamento grupoMedicamento = new GrupoMedicamento(grupoMedicamentoForm.getNome());
		grupoMedicamentoRepository.save(grupoMedicamento);
		URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(grupoMedicamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new GrupoMedicamentoDto(grupoMedicamento));
	}

	public ResponseEntity<GrupoMedicamentoDto> atualizar(BigInteger id, GrupoMedicamentoForm grupoMedicamentoForm) {
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Grupo de Medicamento com id = " + id));
		;
		grupoMedicamento.setNome(grupoMedicamentoForm.getNome());
		return new ResponseEntity<>(new GrupoMedicamentoDto(grupoMedicamento), HttpStatus.OK);
	}

	public ResponseEntity<GrupoMedicamentoDto> remover(BigInteger id) {
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Grupo de Medicamento com id = " + id));
		;
		GrupoMedicamentoDto grupoMedicamentoDto = new GrupoMedicamentoDto(grupoMedicamento);
		grupoMedicamentoRepository.deleteById(id);
		return new ResponseEntity<>(grupoMedicamentoDto, HttpStatus.OK);
	}
	/*
	 * public static List<GrupoMedicamentoDto> converter(List<GrupoMedicamento>
	 * grupoMedicamento){ return
	 * grupoMedicamento.stream().map(GrupoMedicamentoDto::new).collect(Collectors.
	 * toList()); }
	 */
}
