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
import br.com.Calculadora.Dto.MedicamentoConfiguracaoDto;
import br.com.Calculadora.Dto.MedicamentoDto;
import br.com.Calculadora.Exceptions.DuplicateValueException;
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
		Optional<List<Medicamento>> medicamento = Optional.ofNullable(medicamentoRepository.findAll());
		medicamento.orElseThrow(() -> new RecordNotFoundException("Não foram encontrados Medicamentos"));
		List<MedicamentoDto> MedicamentoDtoList = new ArrayList<MedicamentoDto>();
		medicamento.get().forEach(med -> {
			MedicamentoDtoList.add(new MedicamentoDto(med));
		});
		return new ResponseEntity<>(MedicamentoDtoList, HttpStatus.OK);
	}

	public ResponseEntity<MedicamentoDto> lista(BigInteger id) {
		Medicamento medicamento = medicamentoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Medicamento com id = " + id));
		return new ResponseEntity<>(new MedicamentoDto(medicamento), HttpStatus.OK);
	}

	public ResponseEntity<List<MedicamentoDto>> lista(String nome) {
		Optional<List<Medicamento>> medicamentoList = Optional.ofNullable(medicamentoRepository.findByNome(nome));
		medicamentoList.orElseThrow(
				() -> new RecordNotFoundException("Não foram encontrados Medicamentos com o nome = " + nome));
		List<MedicamentoDto> medicamentoDtoList = new ArrayList<MedicamentoDto>();
		medicamentoList.get().forEach(med -> {
			medicamentoDtoList.add(new MedicamentoDto(med));
		});
		return new ResponseEntity<>(medicamentoDtoList, HttpStatus.OK);
	}

	public ResponseEntity<MedicamentoDto> remover(BigInteger id) {
		Medicamento medicamento = medicamentoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Medicamento com id = " + id));
		MedicamentoDto medicamentoDto = new MedicamentoDto(medicamento);
		medicamentoRepository.deleteById(id);
		return new ResponseEntity<>(medicamentoDto, HttpStatus.OK);

	}

	public ResponseEntity<MedicamentoConfiguracaoDto> criar(MedicamentoConfiguracaoForm medicamentoConfiguracaoForm,
			UriComponentsBuilder uriBuilder) {
		BigInteger idLaboratorio = medicamentoConfiguracaoForm.getIdLaboratorio();
		BigInteger idGrupoMedicamento = medicamentoConfiguracaoForm.getIdGrupoMedicamento();
		Laboratorio laboratorio = laboratorioRepository.findById(idLaboratorio)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Laboratorio com id = " + idLaboratorio));
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findById(idGrupoMedicamento)
				.orElseThrow(() -> new RecordNotFoundException(
						"Não encontrado Grupo de Medicamento com id = " + idGrupoMedicamento));
		Medicamento medicamento = new OperacoesService()
				.medicamentoConfiguracaoFormToMedicamento(medicamentoConfiguracaoForm, laboratorio, grupoMedicamento);
		medicamentoRepository.save(medicamento);
		List<DiluicaoConfiguracaoAtualizarForm> diluicaoConfiguracaoFormList = medicamentoConfiguracaoForm
				.getDiluicaoConfiguracaoList();
		List<DiluicaoConfiguracaoDto> diluicaoConfiguracaoList = new ArrayList<>();
		if (!diluicaoConfiguracaoFormList.isEmpty()) {
			for (DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoForm : diluicaoConfiguracaoFormList) {
				ViaAdministracao viaAdministracao = viaAdministracaoRepository
						.findById(diluicaoConfiguracaoForm.getViaAdministracaoId())
						.orElseThrow(() -> new RecordNotFoundException("Não encontrado Via de Administração com id = "
								+ diluicaoConfiguracaoForm.getViaAdministracaoId()));
				DiluicaoConfiguracao diluicaoConfiguracao = new OperacoesService()
						.diluicaoFormToDiluicaoConfiguracao(diluicaoConfiguracaoForm, medicamento, viaAdministracao);
				try {
					diluicaoConfiguracaoRepository.save(diluicaoConfiguracao);
				} catch (RuntimeException e) {
					throw new DuplicateValueException(
							"Não foi possível inserir a Diluição Configuração = " + diluicaoConfiguracao.toString());
				}
				diluicaoConfiguracaoList.add(new DiluicaoConfiguracaoDto(diluicaoConfiguracao));
			}
		}
		URI uri = uriBuilder.path("/criar/{id}").buildAndExpand(medicamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new MedicamentoConfiguracaoDto(medicamento, diluicaoConfiguracaoList));
	}

	public ResponseEntity<MedicamentoConfiguracaoDto> atualizar(BigInteger id,
			MedicamentoConfiguracaoForm medicamentoConfiguracaoForm) {
		BigInteger idLaboratorio = medicamentoConfiguracaoForm.getIdLaboratorio();
		BigInteger idGrupoMedicamento = medicamentoConfiguracaoForm.getIdGrupoMedicamento();
		Medicamento medicamento = medicamentoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Medicamento com id = " + id));
		Laboratorio laboratorio = laboratorioRepository.findById(idLaboratorio)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Laboratorio com id = " + idLaboratorio));
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findById(idGrupoMedicamento)
				.orElseThrow(() -> new RecordNotFoundException(
						"Não encontrado Grupo de Medicamento com id = " + idGrupoMedicamento));
		medicamento = new OperacoesService().medicamentoFormToMedicamento(medicamento, laboratorio, grupoMedicamento,
				medicamentoConfiguracaoForm);
		List<DiluicaoConfiguracaoAtualizarForm> diluicaoConfiguracaoFormList = medicamentoConfiguracaoForm
				.getDiluicaoConfiguracaoList();
		List<DiluicaoConfiguracaoDto> diluicaoConfiguracaoList = new ArrayList<>();
		if (!diluicaoConfiguracaoFormList.isEmpty()) {
			for (DiluicaoConfiguracaoAtualizarForm diluicaoConfiguracaoForm : diluicaoConfiguracaoFormList) {
				ViaAdministracao viaAdministracao = viaAdministracaoRepository
						.findById(diluicaoConfiguracaoForm.getViaAdministracaoId())
						.orElseThrow(() -> new RecordNotFoundException("Não encontrado Via de Administração com id = "
								+ diluicaoConfiguracaoForm.getViaAdministracaoId()));
				DiluicaoConfiguracaoPK diluicaoConfiguracaoPK = new DiluicaoConfiguracaoPK(medicamento,
						viaAdministracao, diluicaoConfiguracaoForm.getSequencia());
				DiluicaoConfiguracao diluicaoConfiguracao = diluicaoConfiguracaoRepository
						.getById(diluicaoConfiguracaoPK);
				DiluicaoConfiguracaoDto diluicaoConfiguracaoDto = new DiluicaoConfiguracaoDto(new OperacoesService()
						.diluicaoAtualizarFormToDiluicao(diluicaoConfiguracao, diluicaoConfiguracaoForm));
				diluicaoConfiguracaoList.add(diluicaoConfiguracaoDto);
			}
		}
		return new ResponseEntity<>((new MedicamentoConfiguracaoDto(medicamento, diluicaoConfiguracaoList)),
				HttpStatus.OK);
	}
}
