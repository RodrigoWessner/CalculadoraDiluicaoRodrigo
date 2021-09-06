package br.com.Calculadora.Controller;

import java.math.BigInteger;

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

import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.Dto.LaboatorioDto;
import br.com.Calculadora.Form.LaboratorioForm;
import br.com.Calculadora.Service.LaboratorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;

@Api(value = "API REST Laboratorio")
@RestController
@RequestMapping("/Laboratorio")
public class LaboratorioController {

	LaboratorioService laboratorioService;

	@Autowired
	public LaboratorioController(LaboratorioService laboratorioService) {
		this.laboratorioService = laboratorioService;
	}
	
	@ApiOperation(value = "Retorna lista da base")
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public Iterable<Laboratorio> lista(){
		return laboratorioService.lista();
	}
	
	@ApiOperation(value = "Insere nome Laboratorio")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<LaboatorioDto> criar(@RequestBody @Valid LaboratorioForm laboratorioForm, BindingResult result, UriComponentsBuilder uriBuilder){
		return(laboratorioService.criar(laboratorioForm, result, uriBuilder));
	}
	
	@ApiOperation(value = "Atualiza Laboratorio")
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<LaboatorioDto> atualizar(@PathVariable BigInteger id, @Valid LaboratorioForm laboratorioForm, BindingResult result){
		return(laboratorioService.atualizar(id, laboratorioForm, result));
	}

	@ApiOperation(value = "Remove Laboratorio")
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<LaboatorioDto> remover(@PathVariable BigInteger id){
		return(laboratorioService.remover(id));
	}
	
}
