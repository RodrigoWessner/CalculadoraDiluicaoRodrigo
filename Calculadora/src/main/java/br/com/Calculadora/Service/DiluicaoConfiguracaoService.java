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

import br.com.Calculadora.Dto.DiluicaoConfiguracaoDto;
import br.com.Calculadora.Exceptions.DuplicateValueException;
import br.com.Calculadora.Exceptions.RecordNotFoundException;
import br.com.Calculadora.Form.DiluicaoConfiguracaoAtualizarSemPKForm;
import br.com.Calculadora.Form.DiluicaoConfiguracaoForm;
import br.com.Calculadora.Repository.DiluicaoConfiguracaoRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.Repository.ViaAdministracaoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.DiluicaoConfiguracaoPK;
import br.com.Calculadora.orm.Medicamento;
import br.com.Calculadora.orm.ViaAdministracao;

@Service
public class DiluicaoConfiguracaoService {

	DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository;
	MedicamentoRepository medicamentoRepository;
	ViaAdministracaoRepository viaAdministracaoRepository;

	@Autowired
	public DiluicaoConfiguracaoService(DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository,
			MedicamentoRepository medicamentoRepository, ViaAdministracaoRepository viaAdministracaoRepository) {
		this.diluicaoConfiguracaoRepository = diluicaoConfiguracaoRepository;
		this.medicamentoRepository = medicamentoRepository;
		this.viaAdministracaoRepository = viaAdministracaoRepository;
	}

	public ResponseEntity<List<DiluicaoConfiguracaoDto>> listar() {
		Optional<List<DiluicaoConfiguracao>> diluicaoConfiguracao = Optional
				.ofNullable(diluicaoConfiguracaoRepository.findAll());
		diluicaoConfiguracao
				.orElseThrow(() -> new RecordNotFoundException("Não foram encontrados Diluções Configurações"));
		List<DiluicaoConfiguracaoDto> diluicaoList = new ArrayList<DiluicaoConfiguracaoDto>();
		diluicaoConfiguracao.get().forEach(diluicao -> {
			diluicaoList.add(new DiluicaoConfiguracaoDto(diluicao));
		});
		return new ResponseEntity<>(diluicaoList, HttpStatus.OK);
	}

	public ResponseEntity<DiluicaoConfiguracaoDto> criar(DiluicaoConfiguracaoForm diluicaoConfiguracaoForm,
			UriComponentsBuilder uriBuilder) {
		BigInteger medicamentoId = diluicaoConfiguracaoForm.getMedicamentoId();
		Medicamento medicamento = medicamentoRepository.findById(medicamentoId)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Medicamento com id = " + medicamentoId));
		BigInteger viaAdministracaoId = diluicaoConfiguracaoForm.getViaAdministracaoId();
		ViaAdministracao viaAdministracao = viaAdministracaoRepository.findById(viaAdministracaoId)
				.orElseThrow(() -> new RecordNotFoundException(
						"Não foi encontrado o Via de Administração com o id = " + viaAdministracaoId));
		DiluicaoConfiguracao diluicaoConfiguracao = new OperacoesService()
				.diluicaoFormToDiluicaoConfiguracao(diluicaoConfiguracaoForm, medicamento, viaAdministracao);
		try {
			diluicaoConfiguracaoRepository.save(diluicaoConfiguracao);
		} catch (RuntimeException e) {
			throw new DuplicateValueException(
					"Não foi possível inserir a Diluição Configuração = " + diluicaoConfiguracao.toString());
		}
		URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(diluicaoConfiguracao.getDiluicaoConfiguracaoPK())
				.toUri();
		return ResponseEntity.created(uri).body(new DiluicaoConfiguracaoDto(diluicaoConfiguracao));
	}

	public ResponseEntity<DiluicaoConfiguracaoDto> remover(BigInteger medicamentoId, BigInteger viaAdministracaoId,
			BigInteger sequencia) {
		Medicamento medicamento = medicamentoRepository.getById(medicamentoId);
		ViaAdministracao viaAdministracao = viaAdministracaoRepository.getById(viaAdministracaoId);
		DiluicaoConfiguracaoPK diluicaoConfiguracaoPK = new DiluicaoConfiguracaoPK(medicamento, viaAdministracao,
				sequencia);
		Optional<DiluicaoConfiguracao> diluicaoConfiguracao = diluicaoConfiguracaoRepository
				.findById(diluicaoConfiguracaoPK);
		diluicaoConfiguracao.orElseThrow(() -> new RecordNotFoundException(
				"Não foi encontrado o Diluição Configuração com o id = " + diluicaoConfiguracao.toString()));
		DiluicaoConfiguracaoDto diluicaoConfiguracaoDto = new DiluicaoConfiguracaoDto(diluicaoConfiguracao.get());
		diluicaoConfiguracaoRepository.deleteById(diluicaoConfiguracaoPK);
		return new ResponseEntity<>(diluicaoConfiguracaoDto, HttpStatus.OK);
	}

	public ResponseEntity<DiluicaoConfiguracaoDto> atualizar(BigInteger medicamentoId, BigInteger viaAdministracaoId,
			BigInteger sequencia, DiluicaoConfiguracaoAtualizarSemPKForm diluicaoConfiguracaoAtualizarSemPKForm) {
		Medicamento medicamento = medicamentoRepository.findById(medicamentoId)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Medicamento com id = " + medicamentoId));
		ViaAdministracao viaAdministracao = viaAdministracaoRepository.findById(viaAdministracaoId)
				.orElseThrow(() -> new RecordNotFoundException(
						"Não foi encontrado o Via de Administração com o id = " + viaAdministracaoId));
		DiluicaoConfiguracaoPK diluicaoConfiguracaoPK = new DiluicaoConfiguracaoPK(medicamento, viaAdministracao,
				sequencia);
		Optional<DiluicaoConfiguracao> diluicaoConfiguracao = diluicaoConfiguracaoRepository
				.findById(diluicaoConfiguracaoPK);
		diluicaoConfiguracao.orElseThrow(() -> new RecordNotFoundException(
				"Não foi encontrado Diluicao Configuração com o id = " + diluicaoConfiguracao.get().toString()));
		DiluicaoConfiguracaoDto diluicaoConfiguracaoDto = new DiluicaoConfiguracaoDto(
				new OperacoesService().diluicaoAtualizarSemPKFormToDiluicao(diluicaoConfiguracao.get(),
						diluicaoConfiguracaoAtualizarSemPKForm));
		return new ResponseEntity<>(diluicaoConfiguracaoDto, HttpStatus.OK);
	}
}
