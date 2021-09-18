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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.LaboatorioDto;
import br.com.Calculadora.Form.LaboratorioForm;
import br.com.Calculadora.Service.LaboratorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Laboratorio")
@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

	LaboratorioService laboratorioService;

	@Autowired
	public LaboratorioController(LaboratorioService laboratorioService) {
		this.laboratorioService = laboratorioService;
	}
	
	@ApiOperation(value = "Retorna lista da base")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<LaboatorioDto>> lista(){
		return(laboratorioService.lista());
	}
	
	@ApiOperation(value = "Retorna entidade do id")
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET, params = "id")
	public ResponseEntity<LaboatorioDto> lista(@RequestParam BigInteger id){
		return(laboratorioService.lista(id));
	}
	
	@ApiOperation(value = "Retorna entidade do nome")
	@RequestMapping(value = "/listar/{nome}", method = RequestMethod.GET, params = "nome")
	public ResponseEntity<LaboatorioDto> lista(@RequestParam String nome){
		return(laboratorioService.lista(nome));
	}
	
	@ApiOperation(value = "Insere nome Laboratorio")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<LaboatorioDto> criar(@RequestBody LaboratorioForm laboratorioForm, UriComponentsBuilder uriBuilder){
		return(laboratorioService.criar(laboratorioForm, uriBuilder));
	}
	
	@ApiOperation(value = "Atualizar Laboratorio")
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<LaboatorioDto> atualizar(@PathVariable BigInteger id, @RequestBody LaboratorioForm laboratorioForm){
		return(laboratorioService.atualizar(id, laboratorioForm));
	}

	@ApiOperation(value = "Remove Laboratorio")
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<LaboatorioDto> remover(@PathVariable BigInteger id){
		return(laboratorioService.remover(id));
	}
	
	
	
}
