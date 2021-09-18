package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.ViaAdministracaoDTO;
import br.com.Calculadora.Exceptions.RecordNotFoundException;
import br.com.Calculadora.Form.ViaAdministracaoForm;
import br.com.Calculadora.Repository.ViaAdministracaoRepository;
import br.com.Calculadora.orm.ViaAdministracao;

@Service
public class ViaAdministracaoService {

	ViaAdministracaoRepository viaAdministracaoRepository;

	@Autowired
	public ViaAdministracaoService(ViaAdministracaoRepository viaAdministracaoRepository) {
		this.viaAdministracaoRepository = viaAdministracaoRepository;
	}

	// metodos
	public ResponseEntity<List<ViaAdministracaoDTO>> lista() {
		List<ViaAdministracao> viaAdministracao = viaAdministracaoRepository.findAll();
		if (viaAdministracao == null) {
			throw new RecordNotFoundException("Não foram encontrados Vias de Administração");
		}
		List<ViaAdministracaoDTO> viaAdministracaoList = new ArrayList<ViaAdministracaoDTO>();
		viaAdministracao.forEach(via -> {
			viaAdministracaoList.add(new ViaAdministracaoDTO(via));
		});
		return new ResponseEntity<>(viaAdministracaoList, HttpStatus.OK);
	}

	public ResponseEntity<ViaAdministracaoDTO> lista(BigInteger id) {
		Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository.findById(id);
		if (!viaAdministracao.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Via de Administração com o id = " + id);
		}
		return new ResponseEntity<>(new ViaAdministracaoDTO(viaAdministracao.get()), HttpStatus.OK);
	}

	public ResponseEntity<ViaAdministracaoDTO> lista(String nome) {
		ViaAdministracao viaAdministracao = viaAdministracaoRepository.findByNome(nome);
		if (viaAdministracao == null) {
			throw new RecordNotFoundException("Não encontrado Via de Administração com o nome = " + nome);
		}
		return new ResponseEntity<>(new ViaAdministracaoDTO(viaAdministracao), HttpStatus.OK);
	}

	public ResponseEntity<ViaAdministracaoDTO> criar(ViaAdministracaoForm viaAdministracaoForm,
			UriComponentsBuilder uriBuilder) {
		ViaAdministracao viaAdministracao = new ViaAdministracao(viaAdministracaoForm.getNome());
		viaAdministracaoRepository.save(viaAdministracao);
		URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(viaAdministracao.getId()).toUri();
		return ResponseEntity.created(uri).body(new ViaAdministracaoDTO(viaAdministracao));
	}

	public ResponseEntity<ViaAdministracaoDTO> atualizar(BigInteger id, ViaAdministracaoForm viaAdministracaoForm) {
		Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository.findById(id);
		if (!viaAdministracao.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Via de Administração com o id = " + id);
		}
		viaAdministracao.get().setNome(viaAdministracaoForm.getNome());
		return new ResponseEntity<>(new ViaAdministracaoDTO(viaAdministracao.get()), HttpStatus.OK);
	}

	public ResponseEntity<ViaAdministracaoDTO> remover(BigInteger id) {
		Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository.findById(id);
		if (!viaAdministracao.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Via de Administração com o id = " + id);
		}
		ViaAdministracaoDTO viaAdministracaoDto = new ViaAdministracaoDTO(viaAdministracao.get());
		viaAdministracaoRepository.deleteById(id);
		return new ResponseEntity<>(viaAdministracaoDto, HttpStatus.OK);
	}

}
