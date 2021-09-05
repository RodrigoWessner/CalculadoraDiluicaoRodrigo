package br.com.Calculadora.Controller;

import java.math.BigInteger;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.Service.MedicamentoService;
import br.com.Calculadora.orm.Medicamento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API REST Medicamentos")
@RequestMapping("/Medicamento")
public class MedicamentoController {

	MedicamentoService medicamentoService;

	@Autowired
	public MedicamentoController(MedicamentoService medicamentoService) {
		this.medicamentoService = medicamentoService;
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ApiOperation(value = "Listar Medicamentos")
	public Iterable<Medicamento> lista(){
		return(medicamentoService.lista());
	}
	
	@ApiOperation(value = "Criar Medicamento")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<MedicamentoDto> criar(@RequestBody MedicamentoForm medicamentoForm, UriComponentsBuilder uriBuilder){
		return medicamentoService.criar(medicamentoForm, uriBuilder);
	}
	
	@ApiOperation(value = "Deletar Medicamento")
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<MedicamentoDto> deletar(@PathVariable BigInteger id){
		return medicamentoService.remover(id);
	}
	
	@ApiOperation(value = "Atualizar Medicamento")
	@RequestMapping(value = "/atulizar/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<MedicamentoDto> atualizar(@PathVariable BigInteger id, @Valid MedicamentoForm medicamentoForm){
		return(medicamentoService.atualizar(id, medicamentoForm));
	}

}
