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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.DiluicaoConfiguracaoDto;
import br.com.Calculadora.Form.DiluicaoConfiguracaoAtualizarForm;
import br.com.Calculadora.Form.DiluicaoConfiguracaoForm;
import br.com.Calculadora.Service.DiluicaoConfiguracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API REST Diluição Configuração")
@RequestMapping("/diluicaoConfiguracao")
public class DiluicaoConfiguracaoController {

	DiluicaoConfiguracaoService diluicaoConfiguracaoService;

	@Autowired
	public DiluicaoConfiguracaoController(DiluicaoConfiguracaoService diluicaoConfiguracaoService) {
		this.diluicaoConfiguracaoService = diluicaoConfiguracaoService;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ApiOperation(value = "Listar Diluição Configuração")
	public ResponseEntity<List<DiluicaoConfiguracaoDto>> lista() {
		return (diluicaoConfiguracaoService.lista());
	}

	@ApiOperation(value = "Criar Diluição Configuração")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<DiluicaoConfiguracaoDto> criar(@RequestBody DiluicaoConfiguracaoForm diluicaoConfiguracaoForm,
			UriComponentsBuilder uriBuilder) {
		return diluicaoConfiguracaoService.criar(diluicaoConfiguracaoForm, uriBuilder);
	}

	@ApiOperation(value = "Deletar Diluição Configuração")
	@RequestMapping(value = "/remover/{medicamentoId}_{viaAdministracaoId}_{sequencia}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<DiluicaoConfiguracaoDto> deletar(@PathVariable BigInteger medicamentoId, @PathVariable BigInteger viaAdministracaoId, @PathVariable BigInteger sequencia) {
		return diluicaoConfiguracaoService.remover(medicamentoId,viaAdministracaoId,sequencia);
	}

	@ApiOperation(value = "Atualizar Diluição Configuração")
	@RequestMapping(value = "/atualizar/{medicamentoId}_{viaAdministracaoId}_{sequencia}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<DiluicaoConfiguracaoDto> atualizar(@PathVariable BigInteger medicamentoId, @PathVariable BigInteger viaAdministracaoId, @PathVariable BigInteger sequencia, DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoAtualizarForm) {
		return (diluicaoConfiguracaoService.atualizar(medicamentoId,viaAdministracaoId,sequencia, diluicaoConfiguracaoAtualizarForm));
	}

}
