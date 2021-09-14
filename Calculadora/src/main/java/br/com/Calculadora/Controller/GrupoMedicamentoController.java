package br.com.Calculadora.Controller;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.GrupoMedicamentoDto;
import br.com.Calculadora.Form.GrupoMedicamentoForm;
import br.com.Calculadora.Service.GrupoMedicamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Grupo_Medicamento")
@RestController
@RequestMapping("/grupoMedicamento")
public class GrupoMedicamentoController {

	GrupoMedicamentoService grupoMedicamentoService;

	@Autowired
	public GrupoMedicamentoController(GrupoMedicamentoService grupoMedicamentoService) {
		this.grupoMedicamentoService = grupoMedicamentoService;
	}

	@ApiOperation(value = "Retorna lista da base")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<GrupoMedicamentoDto>> lista() {
		return grupoMedicamentoService.lista();
	}
	
	@ApiOperation(value = "Retorna entidade do id")
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET, params = "id")
	public ResponseEntity<GrupoMedicamentoDto> lista(@RequestParam BigInteger id){
		return(grupoMedicamentoService.lista(id));
	}
	
	@ApiOperation(value = "Retorna entidade do nome")
	@RequestMapping(value = "/listar/{nome}", method = RequestMethod.GET, params = "nome")
	public ResponseEntity<GrupoMedicamentoDto> lista(@RequestParam String nome){
		return(grupoMedicamentoService.lista(nome));
	}

	@ApiOperation(value = "Insere nome Grupo_Medicamento")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<GrupoMedicamentoDto> criar(@RequestBody @Valid GrupoMedicamentoForm grupoMedicamentoForm,
			UriComponentsBuilder uriBuilder) {// pega info do corpo
		return grupoMedicamentoService.criar(grupoMedicamentoForm, uriBuilder);
	}

	@ApiOperation(value = "Atualiza Grupo_Medicamento")
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<GrupoMedicamentoDto> atualizar(@PathVariable BigInteger id,
			@Valid GrupoMedicamentoForm grupoMedicamentoForm) {
		return (grupoMedicamentoService.atualizar(id, grupoMedicamentoForm));
	}

	@ApiOperation(value = "Remove Grupo_Medicamento")
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<GrupoMedicamentoDto> remover(@PathVariable BigInteger id) {
		return (grupoMedicamentoService.remover(id));
	}
}
