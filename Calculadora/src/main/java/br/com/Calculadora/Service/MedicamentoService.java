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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Calculadora.Dto.DiluicaoConfiguracaoDto;
import br.com.Calculadora.Dto.MedicamentoConfiguracaoDto;
import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Exceptions.RecordNotFoundException;
import br.com.Calculadora.Form.DiluicaoConfiguracaoAtualizarForm;
import br.com.Calculadora.Form.MedicamentoConfiguracaoForm;
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
public class MedicamentoService {

	MedicamentoRepository medicamentoRepository;
	LaboratorioRepository laboratorioRepository;
	GrupoMedicamentoRepository grupoMedicamentoRepository;
	ViaAdministracaoRepository viaAdministracaoRepository;
	DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository;

	@Autowired
	public MedicamentoService(MedicamentoRepository medicamentoRepository, LaboratorioRepository laboratorioRepository,
			GrupoMedicamentoRepository grupoMedicamentoRepository,
			ViaAdministracaoRepository viaAdministracaoRepository,
			DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository) {
		this.medicamentoRepository = medicamentoRepository;
		this.laboratorioRepository = laboratorioRepository;
		this.grupoMedicamentoRepository = grupoMedicamentoRepository;
		this.viaAdministracaoRepository = viaAdministracaoRepository;
		this.diluicaoConfiguracaoRepository = diluicaoConfiguracaoRepository;
	}

	public ResponseEntity<List<MedicamentoDto>> lista() {
		List<Medicamento> medicamento = medicamentoRepository.findAll();
		if (medicamento == null) {
			throw new RecordNotFoundException("Não foram encontrados Medicamentos");
		}
		List<MedicamentoDto> MedicamentoDtoList = new ArrayList<MedicamentoDto>();
		medicamento.forEach(med -> {
			MedicamentoDtoList.add(new MedicamentoDto(med));
		});
		return new ResponseEntity<>(MedicamentoDtoList, HttpStatus.OK);
	}

	public ResponseEntity<MedicamentoDto> lista(BigInteger id) {
		Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
		MedicamentoDto medicamentoDto = new MedicamentoDto(medicamento.get());
		return ResponseEntity.ok(medicamentoDto);
	}

	public ResponseEntity<List<MedicamentoDto>> lista(String nome) {
		List<Medicamento> medicamentoList = medicamentoRepository.findByNome(nome);
		if (medicamentoList.isEmpty()) {
			throw new RecordNotFoundException("Não foram encontrados Medicamentos com o nome = " + nome);
		}
		List<MedicamentoDto> medicamentoDtoList = new ArrayList<MedicamentoDto>();
		medicamentoList.forEach(med -> {
			medicamentoDtoList.add(new MedicamentoDto(med));
		});
		return new ResponseEntity<>(medicamentoDtoList, HttpStatus.OK);
	}

	/*
	 * INSERIR SOMENTE O MEDICAMENTO public ResponseEntity<MedicamentoDto>
	 * criar(MedicamentoForm medicamentoForm, UriComponentsBuilder uriBuilder) { if
	 * ((grupoMedicamentoRepository.findById(medicamentoForm.getIdGrupoMedicamento()
	 * ) == null) ||
	 * (laboratorioRepository.findById(medicamentoForm.getIdLaboratorio()) == null))
	 * { throw new RuntimeException(); } else { Optional<Laboratorio> laboratorio =
	 * laboratorioRepository.findById(medicamentoForm.getIdLaboratorio());
	 * Optional<GrupoMedicamento> grupoMedicamento = grupoMedicamentoRepository
	 * .findById(medicamentoForm.getIdGrupoMedicamento()); Medicamento medicamento =
	 * new OperacoesService().medicamentoFormToMedicamento(medicamentoForm,
	 * laboratorio.get(), grupoMedicamento.get());
	 * medicamentoRepository.save(medicamento);
	 * 
	 * URI uri =
	 * uriBuilder.path("/criar/{id}").buildAndExpand(medicamento.getId()).toUri();
	 * return ResponseEntity.created(uri).body(new MedicamentoDto(medicamento)); } }
	 */

	public ResponseEntity<MedicamentoDto> remover(BigInteger id) {
		Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
		if (!medicamento.isPresent()) {
			throw new RecordNotFoundException("Não foram encontrados Medicamentos com o id = " + id);
		}
		MedicamentoDto medicamentoDto = new MedicamentoDto(medicamento.get());
		medicamentoRepository.deleteById(id);
		return new ResponseEntity<>(medicamentoDto, HttpStatus.OK);

	}

	/*
	 * ATUALIZAR SOMENTE UM MEDICAMENTO public ResponseEntity<MedicamentoDto>
	 * atualizar(BigInteger id, MedicamentoForm medicamentoForm) { if
	 * (!medicamentoRepository.existsById(id) ||
	 * (grupoMedicamentoRepository.findById(medicamentoForm.getIdGrupoMedicamento())
	 * == null) ||
	 * (laboratorioRepository.findById(medicamentoForm.getIdLaboratorio()) == null))
	 * { throw new RuntimeException(); } else { try { Medicamento medicamento = new
	 * OperacoesService().medicamentoFormToMedicamento(id, medicamentoForm,
	 * medicamentoRepository, grupoMedicamentoRepository, laboratorioRepository);
	 * return (ResponseEntity.ok(new MedicamentoDto(medicamento))); } catch
	 * (RuntimeException exception) { throw exception; } } }
	 */

	// INSERE O MEDICAMENTO E SUA CONFIGURACAO
	public ResponseEntity<MedicamentoConfiguracaoDto> criar(MedicamentoConfiguracaoForm medicamentoConfiguracaoForm,
			UriComponentsBuilder uriBuilder) {
		BigInteger idLaboratorio = medicamentoConfiguracaoForm.getIdLaboratorio();
		BigInteger idGrupoMedicamento = medicamentoConfiguracaoForm.getIdGrupoMedicamento();
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(idLaboratorio);
		if (!laboratorio.isPresent()) {
			throw new RecordNotFoundException("Não foi encontrado Laboratorio com id = " + idLaboratorio);
		}
		Optional<GrupoMedicamento> grupoMedicamento = grupoMedicamentoRepository.findById(idGrupoMedicamento);
		if (!grupoMedicamento.isPresent()) {
			throw new RecordNotFoundException("Não foi encontrado Grupo Medicamento com id = " + idGrupoMedicamento);
		}
		Medicamento medicamento = new OperacoesService().medicamentoConfiguracaoFormToMedicamento(
				medicamentoConfiguracaoForm, laboratorio.get(), grupoMedicamento.get());

		medicamentoRepository.save(medicamento);
		// BigInteger idMedicamento = medicamento.getId();

		List<DiluicaoConfiguracaoAtualizarForm> diluicaoConfiguracaoFormList = medicamentoConfiguracaoForm
				.getDiluicaoConfiguracaoList();
		List<DiluicaoConfiguracaoDto> diluicaoConfiguracaoList = new ArrayList<>();
		if (!diluicaoConfiguracaoFormList.isEmpty()) {
			for (DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoForm : diluicaoConfiguracaoFormList) {
				Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository
						.findById(diluicaoConfiguracaoForm.getViaAdministracaoId());
				if (!viaAdministracao.isPresent()) {
					throw new RecordNotFoundException("Não foi encontrado Via de Administração com id = "
							+ diluicaoConfiguracaoForm.getViaAdministracaoId());
				}
				DiluicaoConfiguracao diluicaoConfiguracao = new OperacoesService().diluicaoFormToDiluicaoConfiguracao(
						diluicaoConfiguracaoForm, medicamento, viaAdministracao.get());
				diluicaoConfiguracaoRepository.save(diluicaoConfiguracao);
				diluicaoConfiguracaoList.add(new DiluicaoConfiguracaoDto(diluicaoConfiguracao));
			}
		}
		URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(medicamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new MedicamentoConfiguracaoDto(medicamento, diluicaoConfiguracaoList));
	}

	// ATUALIZA EM MASSA O MEDICAMENTO E SUA CONFIGURACAO
	@Transactional
	public ResponseEntity<MedicamentoConfiguracaoDto> atualizar(BigInteger id,
			MedicamentoConfiguracaoForm medicamentoConfiguracaoForm) {
		BigInteger idLaboratorio = medicamentoConfiguracaoForm.getIdLaboratorio();
		BigInteger idGrupoMedicamento = medicamentoConfiguracaoForm.getIdGrupoMedicamento();
		Medicamento medicamento = medicamentoRepository.getById(id);
		if (medicamento == null) {
			throw new RecordNotFoundException("Não encontrado Medicamento com id = " + id);
		}
		Laboratorio laboratorio = laboratorioRepository.getById(idLaboratorio);
		if (laboratorio == null) {
			throw new RecordNotFoundException("Não encontrado Laboratorio com id = " + idLaboratorio);
		}
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.getById(idGrupoMedicamento);
		if (grupoMedicamento == null) {
			throw new RecordNotFoundException("Não encontrado Grupo de Medicamento com id = " + idGrupoMedicamento);
		}
		medicamento = new OperacoesService().medicamentoFormToMedicamento(medicamento, laboratorio, grupoMedicamento,
				medicamentoConfiguracaoForm);

		List<DiluicaoConfiguracaoAtualizarForm> diluicaoConfiguracaoFormList = medicamentoConfiguracaoForm
				.getDiluicaoConfiguracaoList();
		List<DiluicaoConfiguracaoDto> diluicaoConfiguracaoList = new ArrayList<>();
		if (!diluicaoConfiguracaoFormList.isEmpty()) {
			for (DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoForm : diluicaoConfiguracaoFormList) {
				ViaAdministracao viaAdministracao = viaAdministracaoRepository
						.getById(diluicaoConfiguracaoForm.getViaAdministracaoId());
				if (viaAdministracao == null) {
					throw new RecordNotFoundException("Não encontrado Via de Administração com id = "
							+ diluicaoConfiguracaoForm.getViaAdministracaoId());
				}
				DiluicaoConfiguracaoPK diluicaoConfiguracaoPK = new DiluicaoConfiguracaoPK(medicamento,
						viaAdministracao, diluicaoConfiguracaoForm.getSequencia());
				// VERIFICAR SEQUENCIA
				DiluicaoConfiguracao diluicaoConfiguracao = diluicaoConfiguracaoRepository
						.getById(diluicaoConfiguracaoPK);
				DiluicaoConfiguracaoDto diluicaoConfiguracaoDto = new DiluicaoConfiguracaoDto(new OperacoesService()
						.diluicaoAtualizarFormToDiluicao(diluicaoConfiguracao, diluicaoConfiguracaoForm));
/////
				/*
				 * DiluicaoConfiguracao diluicaoConfiguracao = new OperacoesService()
				 * .diluicaoAtualizarFormToDiluicao(medicamento.getId(),
				 * diluicaoConfiguracaoForm.getViaAdministracaoId(),
				 * diluicaoConfiguracaoForm.getSequencia(), diluicaoConfiguracaoForm,
				 * medicamentoRepository, viaAdministracaoRepository,
				 * diluicaoConfiguracaoRepository);
				 * 
				 * diluicaoConfiguracaoList.add(new
				 * DiluicaoConfiguracaoDto(diluicaoConfiguracao));
				 */
				diluicaoConfiguracaoList.add(diluicaoConfiguracaoDto);
			}
		}
		return new ResponseEntity<>((new MedicamentoConfiguracaoDto(medicamento, diluicaoConfiguracaoList)),
				HttpStatus.OK);
	}
}
