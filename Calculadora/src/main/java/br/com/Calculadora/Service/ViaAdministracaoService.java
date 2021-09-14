package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.ViaAdministracaoDTO;
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
		List<ViaAdministracaoDTO> viaAdministracaoList = new ArrayList<ViaAdministracaoDTO>();
		viaAdministracao.forEach(via -> {
			viaAdministracaoList.add(new ViaAdministracaoDTO(via));
		});
		return ResponseEntity.ok(viaAdministracaoList);
	}

	public ResponseEntity<ViaAdministracaoDTO> lista(BigInteger id) {
		Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository.findById(id);
		ViaAdministracaoDTO viaAdministracaoDTO = new ViaAdministracaoDTO(viaAdministracao.get());
		return ResponseEntity.ok(viaAdministracaoDTO);
	}

	public ResponseEntity<ViaAdministracaoDTO> lista(String nome) {
		ViaAdministracao viaAdministracao = viaAdministracaoRepository.findByNome(nome);
		ViaAdministracaoDTO viaAdministracaoDTO = new ViaAdministracaoDTO(viaAdministracao);
		return ResponseEntity.ok(viaAdministracaoDTO);
	}

	public ResponseEntity<ViaAdministracaoDTO> criar(ViaAdministracaoForm viaAdministracaoForm,
			UriComponentsBuilder uriBuilder) {
		try {
			ViaAdministracao viaAdministracao = new ViaAdministracao(viaAdministracaoForm.getNome());
			viaAdministracaoRepository.save(viaAdministracao);

			URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(viaAdministracao.getId()).toUri();
			return ResponseEntity.created(uri).body(new ViaAdministracaoDTO(viaAdministracao));
		} catch (RuntimeException exception) {
			throw exception;
		}
	}

	public ResponseEntity<ViaAdministracaoDTO> atualizar(BigInteger id, ViaAdministracaoForm viaAdministracaoForm) {
		if (!viaAdministracaoRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			try {
				ViaAdministracao viaAdministracao = viaAdministracaoRepository.getById(id);
				viaAdministracao.setNome(viaAdministracaoForm.getNome());
				return ResponseEntity.ok(new ViaAdministracaoDTO(viaAdministracao));
			} catch (RuntimeException exception) {
				throw exception;
			}
		}
	}

	public ResponseEntity<ViaAdministracaoDTO> remover(BigInteger id) {
		if (!viaAdministracaoRepository.existsById(id)) {
			throw new RuntimeException();
		} else {
			Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository.findById(id);
			ViaAdministracaoDTO viaAdministracaoDto = new ViaAdministracaoDTO(viaAdministracao.get());
			viaAdministracaoRepository.deleteById(id);
			return (ResponseEntity.ok(viaAdministracaoDto));
		}
	}
}
