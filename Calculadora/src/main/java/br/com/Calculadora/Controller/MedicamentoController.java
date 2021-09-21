package br.com.Calculadora.Controller;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.MedicamentoConfiguracaoDto;
import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Form.MedicamentoConfiguracaoForm;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Service.MedicamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API REST Medicamentos")
@RequestMapping("/medicamento")
public class MedicamentoController {

	MedicamentoService medicamentoService;

	@Autowired
	public MedicamentoController(MedicamentoService medicamentoService) {
		this.medicamentoService = medicamentoService;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ApiOperation(value = "Listar Medicamentos")
	public ResponseEntity<List<MedicamentoDto>> lista() {
		return (medicamentoService.lista());
	}

	@ApiOperation(value = "Retorna entidade do id")
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET, params = "id")
	public ResponseEntity<MedicamentoDto> lista(@RequestParam BigInteger id) {
		return (medicamentoService.lista(id));
	}

	@ApiOperation(value = "Retorna entidade do nome")
	@RequestMapping(value = "/listar/{nome}", method = RequestMethod.GET, params = "nome")
	public ResponseEntity<List<MedicamentoDto>> lista(@RequestParam String nome) {
		return (medicamentoService.lista(nome));
	}

	/*@ApiOperation(value = "Criar Medicamento")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<MedicamentoDto> criar(@RequestBody MedicamentoForm medicamentoForm,
			UriComponentsBuilder uriBuilder) {
		return medicamentoService.criar(medicamentoForm, uriBuilder);
	}*/

	@ApiOperation(value = "Criar Medicamento")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<MedicamentoConfiguracaoDto> criar(@RequestBody MedicamentoConfiguracaoForm medicamentoConfiguracaoForm,
			UriComponentsBuilder uriBuilder) {
		return medicamentoService.criar(medicamentoConfiguracaoForm, uriBuilder);
	}
	
	@ApiOperation(value = "Deletar Medicamento")
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<MedicamentoDto> deletar(@PathVariable BigInteger id) {
		return medicamentoService.remover(id);
	}

	@ApiOperation(value = "Atualizar Medicamento")
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<MedicamentoConfiguracaoDto> atualizar(@PathVariable BigInteger id, @RequestBody MedicamentoConfiguracaoForm medicamentoConfiguracaoForm) {
		return (medicamentoService.atualizar(id, medicamentoConfiguracaoForm));
	}

}
