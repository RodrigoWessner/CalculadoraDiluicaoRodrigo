package br.com.Calculadora.Controller;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.Calculadora.Dto.GrupoMedicamentoDto;
import br.com.Calculadora.Form.GrupoMedicamentoForm;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Service.GrupoMedicamentoService;
import br.com.Calculadora.orm.GrupoMedicamento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Grupo_Medicamento")
@RestController
@RequestMapping("/GrupoMedicamento")
public class GrupoMedicamentoController {

	GrupoMedicamentoService grupoMedicamentoService;

	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository;

	@Autowired
	public GrupoMedicamentoController(GrupoMedicamentoService grupoMedicamentoService) {
		this.grupoMedicamentoService = grupoMedicamentoService;
	}

	@ApiOperation(value = "Retorna lista da base")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public Iterable<GrupoMedicamento> lista() {
		// CrudPersistence
		/*
		 * List<GrupoMedicamento> grupoMedicamento =
		 * grupoMedicamentoRepository.findAll(); return
		 * GrupoMedicamentoDto.converter(grupoMedicamento);
		 */
		// Iterable<GrupoMedicamento> grupoMedicamento =
		// grupoMedicamentoRepository.findAll();
		return grupoMedicamentoService.lista();

	}

	@ApiOperation(value = "Insere nome Grupo_Medicamento")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<GrupoMedicamentoDto> criar(@RequestBody @Valid GrupoMedicamentoForm grupoMedicamentoForm,
			UriComponentsBuilder uriBuilder, BindingResult result) {// pega info do corpo
		/*
		 * GrupoMedicamento grupoMedicamento = grupoMedicamentoForm.converter();
		 * grupoMedicamentoRepository.save(grupoMedicamento);
		 * 
		 * //boas praticas, retorno 201 URI uri =
		 * uriBuilder.path("/criar/{id}").buildAndExpand(grupoMedicamento.getId()).toUri
		 * (); return ResponseEntity.created(uri).body(new
		 * GrupoMedicamentoDto(grupoMedicamento));
		 */
		return grupoMedicamentoService.criar(grupoMedicamentoForm, uriBuilder, result);
	}

	@ApiOperation(value = "Atualiza Grupo_Medicamento")
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<GrupoMedicamentoDto> atualizar(@PathVariable BigInteger id,
			@Valid GrupoMedicamentoForm grupoMedicamentoForm, BindingResult result) {
		/*
		 * GrupoMedicamento grupoMedicamento = grupoMedicamentoForm.atualizar(id,
		 * grupoMedicamentoRepository); return ResponseEntity.ok(new
		 * GrupoMedicamentoDto(grupoMedicamento));
		 */
		return (grupoMedicamentoService.atualizar(id, grupoMedicamentoForm, result));
	}

	@ApiOperation(value = "Remove Grupo_Medicamento")
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<GrupoMedicamentoDto> remover(@PathVariable BigInteger id) {
		if (!grupoMedicamentoRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			/*
			 * grupoMedicamentoRepository.deleteById(id); return
			 * ResponseEntity.ok().build();
			 */
			return (grupoMedicamentoService.remover(id));
		}
	}
}
