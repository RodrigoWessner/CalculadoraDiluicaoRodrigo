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

import br.com.Calculadora.Dto.ViaAdministracaoDTO;
import br.com.Calculadora.Form.ViaAdministracaoForm;
import br.com.Calculadora.Service.ViaAdministracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Via Administração")
@RestController
@RequestMapping("/viaAdministracao")
public class ViaAdministracaoController {
	ViaAdministracaoService viaAdministracaoService;

	@Autowired
	public ViaAdministracaoController(ViaAdministracaoService viaAdministracaoService) {
		this.viaAdministracaoService = viaAdministracaoService;
	}

	@ApiOperation(value = "Retorna lista da base")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<ViaAdministracaoDTO>> lista() {
		return (viaAdministracaoService.listar());
	}
	
	@ApiOperation(value = "Retorna entidade do id")
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET, params = "id")
	public ResponseEntity<ViaAdministracaoDTO> listar(@RequestParam BigInteger id){
		return(viaAdministracaoService.listar(id));
	}
	
	@ApiOperation(value = "Retorna entidade do nome")
	@RequestMapping(value = "/listar/{nome}", method = RequestMethod.GET, params = "nome")
	public ResponseEntity<ViaAdministracaoDTO> listar(@RequestParam String nome){
		return(viaAdministracaoService.listar(nome));
	}

	@ApiOperation(value = "Insere nome Via Administração")
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public ResponseEntity<ViaAdministracaoDTO> criar(@RequestBody ViaAdministracaoForm viaAdministracaoForm,
			UriComponentsBuilder uriBuilder) {
		return (viaAdministracaoService.criar(viaAdministracaoForm, uriBuilder));
	}

	@ApiOperation(value = "Atualizar Via Administração")
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<ViaAdministracaoDTO> atualizar(@PathVariable BigInteger id,
			@RequestBody ViaAdministracaoForm viaAdministracaoForm) {
		return (viaAdministracaoService.atualizar(id, viaAdministracaoForm));
	}

	@ApiOperation(value = "Remove Via Administração")
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<ViaAdministracaoDTO> remover(@PathVariable BigInteger id) {
		return (viaAdministracaoService.remover(id));
	}
}
