package br.com.Calculadora.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Service.HistoricoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Historico")
@RestController
@RequestMapping("/historico")
public class HistoricoController {

	HistoricoService historicoService;

	@Autowired
	public HistoricoController(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}

	@ApiOperation(value = "Retorna lista da base")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<HistoricoDto>> lista() {
		return (historicoService.lista());
	}

}
