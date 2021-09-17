package br.com.Calculadora.Controller;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Form.CalculoForm;
import br.com.Calculadora.Service.CalculoHistoricoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Historico")
@RestController
@RequestMapping("/calculo")
public class CalculoHistoricoController {

	CalculoHistoricoService calculoHistoricoService;

	@Autowired
	public CalculoHistoricoController(CalculoHistoricoService calculoHistoricoService) {
		this.calculoHistoricoService = calculoHistoricoService;
	}
	
	@ApiOperation(value = "Realiza o calculo de diluicao")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<HistoricoDto> criar(@RequestBody CalculoForm calculoForm){
		return (calculoHistoricoService.criar(calculoForm));
	}

	@ApiOperation(value = "Retorna lista da base")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<HistoricoDto>> lista(@RequestParam BigInteger id, @RequestParam String dataInicio, @RequestParam String dataFim) {
		return (calculoHistoricoService.lista(id, dataInicio, dataFim));
	}

}
