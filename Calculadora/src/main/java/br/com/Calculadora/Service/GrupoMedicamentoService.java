package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
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
	public Iterable<GrupoMedicamento> lista() {
		Iterable<GrupoMedicamento> grupoMedicamento = grupoMedicamentoRepository.findAll();
		return grupoMedicamento;
	}

	public ResponseEntity<GrupoMedicamentoDto> criar(@Valid GrupoMedicamentoForm grupoMedicamentoForm,
			UriComponentsBuilder uriBuilder, BindingResult result) {// pega info do corpo
		if (result.hasErrors()) {
			throw new RuntimeException();
		} else {
			GrupoMedicamento grupoMedicamento = grupoMedicamentoForm.converter();
			grupoMedicamentoRepository.save(grupoMedicamento);

			// boas praticas, retorno 201
			URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(grupoMedicamento.getId()).toUri();
			return ResponseEntity.created(uri).body(new GrupoMedicamentoDto(grupoMedicamento));
		}
	}

	public ResponseEntity<GrupoMedicamentoDto> atualizar(BigInteger id, GrupoMedicamentoForm grupoMedicamentoForm,
			BindingResult result) {
		if (result.hasErrors() || !grupoMedicamentoRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			GrupoMedicamento grupoMedicamento = grupoMedicamentoForm.atualizar(id, grupoMedicamentoRepository);
			return ResponseEntity.ok(new GrupoMedicamentoDto(grupoMedicamento));
		}
	}

	public ResponseEntity<GrupoMedicamentoDto> remover(BigInteger id) {
		grupoMedicamentoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
