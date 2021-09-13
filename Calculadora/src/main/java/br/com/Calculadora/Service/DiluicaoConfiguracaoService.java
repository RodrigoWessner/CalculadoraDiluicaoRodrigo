package br.com.Calculadora.Service;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.DiluicaoConfiguracaoDto;
import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Form.DiluicaoConfiguracaoAtualizarForm;
import br.com.Calculadora.Form.DiluicaoConfiguracaoForm;
import br.com.Calculadora.Form.MedicamentoForm;
import br.com.Calculadora.Repository.DiluicaoConfiguracaoRepository;
import br.com.Calculadora.Repository.GrupoMedicamentoRepository;
import br.com.Calculadora.Repository.LaboratorioRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.Repository.ViaAdministracaoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.DiluicaoConfiguracaoPK;
import br.com.Calculadora.orm.GrupoMedicamento;
import br.com.Calculadora.orm.Laboratorio;
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

	// Cruds

	public ResponseEntity<List<DiluicaoConfiguracaoDto>> lista() {
		List<DiluicaoConfiguracao> diluicaoConfiguracao = diluicaoConfiguracaoRepository.findAll();
		List<DiluicaoConfiguracaoDto> diluicaoList = new ArrayList<DiluicaoConfiguracaoDto>();

		diluicaoConfiguracao.forEach(diluicao -> {
			diluicaoList.add(new DiluicaoConfiguracaoDto(diluicao));
		});

		return ResponseEntity.ok(diluicaoList);
	}

	public ResponseEntity<DiluicaoConfiguracaoDto> criar(DiluicaoConfiguracaoForm diluicaoConfiguracaoForm,
			UriComponentsBuilder uriBuilder) {
		if ((medicamentoRepository.findById(diluicaoConfiguracaoForm.getMedicamentoId()) == null)
				|| (viaAdministracaoRepository.findById(diluicaoConfiguracaoForm.getViaAdministracaoId()) == null)) {
			throw new RuntimeException();
		} else {
			Optional<Medicamento> medicamento = medicamentoRepository
					.findById(diluicaoConfiguracaoForm.getMedicamentoId());
			Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository
					.findById(diluicaoConfiguracaoForm.getViaAdministracaoId());

			DiluicaoConfiguracao diluicaoConfiguracao = new OperacoesService().diluicaoFormToDiluicaoConfiguracao(
					diluicaoConfiguracaoForm, medicamento.get(), viaAdministracao.get());
			diluicaoConfiguracaoRepository.save(diluicaoConfiguracao);

			URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(diluicaoConfiguracao.getDiluicaoConfiguracaoPK())
					.toUri();
			return ResponseEntity.created(uri).body(new DiluicaoConfiguracaoDto(diluicaoConfiguracao));
		}
	}

	public ResponseEntity<DiluicaoConfiguracaoDto> remover(BigInteger medicamentoId, BigInteger viaAdministracaoId,
			BigInteger sequencia) {
		Medicamento medicamento = medicamentoRepository.getById(medicamentoId);
		ViaAdministracao viaAdministracao = viaAdministracaoRepository.getById(viaAdministracaoId);
		DiluicaoConfiguracaoPK diluicaoConfiguracaoPK = new DiluicaoConfiguracaoPK(medicamento, viaAdministracao,
				sequencia);

		if (!diluicaoConfiguracaoRepository.existsById(diluicaoConfiguracaoPK)) {
			throw new RuntimeException();
		} else {
			try {
				Optional<DiluicaoConfiguracao> diluicaoConfiguracao = diluicaoConfiguracaoRepository
						.findById(diluicaoConfiguracaoPK);
				DiluicaoConfiguracaoDto diluicaoConfiguracaoDto = new DiluicaoConfiguracaoDto(
						diluicaoConfiguracao.get());
				diluicaoConfiguracaoRepository.deleteById(diluicaoConfiguracaoPK);
				return (ResponseEntity.ok(diluicaoConfiguracaoDto));
			} catch (RuntimeException exception) {
				throw exception;
			}
		}
	}

	public ResponseEntity<DiluicaoConfiguracaoDto> atualizar(BigInteger medicamentoId, BigInteger viaAdministracaoId,
			BigInteger sequencia, DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoAtualizarForm) {
		

		/*
		 * if (!diluicaoConfiguracaoRepository.existsById(diluicaoConfiguracaoPK) ||
		 * (grupoMedicamentoRepository.findById(medicamentoForm.getIdGrupoMedicamento())
		 * == null) ||
		 * (laboratorioRepository.findById(medicamentoForm.getIdLaboratorio()) == null))
		 * { throw new RuntimeException(); } else {
		 */
		try {
			DiluicaoConfiguracao diluicaoConfiguracao = new OperacoesService().diluicaoAtualizarFormToDiluicao(
					medicamentoId, viaAdministracaoId, sequencia, diluicaoConfiguracaoAtualizarForm, medicamentoRepository,
					viaAdministracaoRepository, diluicaoConfiguracaoRepository);
			return (ResponseEntity.ok(new DiluicaoConfiguracaoDto(diluicaoConfiguracao)));
		} catch (RuntimeException exception) {
			throw exception;
		}

	}
}
