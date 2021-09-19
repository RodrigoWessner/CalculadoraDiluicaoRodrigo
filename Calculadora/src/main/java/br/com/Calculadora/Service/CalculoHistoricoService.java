package br.com.Calculadora.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Exceptions.RecordNotFoundException;
import br.com.Calculadora.Form.CalculoForm;
import br.com.Calculadora.Repository.DiluicaoConfiguracaoRepository;
import br.com.Calculadora.Repository.HistoricoRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.Repository.ViaAdministracaoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.Historico;
import br.com.Calculadora.orm.Medicamento;
import br.com.Calculadora.orm.ViaAdministracao;

@Service
public class CalculoHistoricoService {

	HistoricoRepository historicoRepository;
	DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository;
	MedicamentoRepository medicamentoRepository;
	ViaAdministracaoRepository viaAdministracaoRepository;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	public CalculoHistoricoService(HistoricoRepository historicoRepository,
			DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository, MedicamentoRepository medicamentoRepository,
			ViaAdministracaoRepository viaAdministracaoRepository) {
		this.historicoRepository = historicoRepository;
		this.diluicaoConfiguracaoRepository = diluicaoConfiguracaoRepository;
		this.medicamentoRepository = medicamentoRepository;
		this.viaAdministracaoRepository = viaAdministracaoRepository;
	}

	// metodos
	public ResponseEntity<List<HistoricoDto>> lista(BigInteger id, String dataInicio, String dataFim) {
		List<HistoricoDto> historicoDtoList = new ArrayList<HistoricoDto>();
		LocalDate dataInicial = LocalDate.parse(dataInicio, formatter);
		LocalDate dataFinal = LocalDate.parse(dataFim, formatter);
		Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
		if (!medicamento.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Medicamento com o id = " + id);
		}
		String nomeMedicamento = medicamento.get().getNome();
		List<Historico> historico = historicoRepository.findByNomeAndBetweenDatas(nomeMedicamento, dataInicial,
				dataFinal);
		historico.forEach(his -> {
			historicoDtoList.add(new HistoricoDto(his));
		});
		return new ResponseEntity<>(historicoDtoList, HttpStatus.OK);
	}

	public ResponseEntity<HistoricoDto> criar(CalculoForm calculoForm) {
		BigInteger idMedicamento = calculoForm.getIdMedicamento();
		BigInteger idViaAdministracao = calculoForm.getIdViaAdministracao();
		Optional<Medicamento> medicamento = medicamentoRepository.findById(idMedicamento);
		if (!medicamento.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Medicamento com o id = " + idMedicamento);
		}
		Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository.findById(idViaAdministracao);
		if (!viaAdministracao.isPresent()) {
			throw new RecordNotFoundException("Não encontrado Via de Administração com o id = " + idViaAdministracao);
		}
		
		List<DiluicaoConfiguracao> diluicaoConfiguracaoList = diluicaoConfiguracaoRepository
				.findDiluicaoConfiguracaoIdViaIdMed(idMedicamento, idViaAdministracao);

		BigDecimal prescricao = calculoForm.getPrescricao();
		BigDecimal resultado = prescricao.divide(medicamento.get().getQuantidadeApresentacao(), MathContext.DECIMAL64);

		System.out.println(resultado);
		/*
		 * Historico historico = new Historico(calculoForm.getNomeUsuario(),
		 * medicamento.get().getNome(), medicamento.get().getQuantidadeApresentacao(),
		 * calculoForm.getPrescricao(), viaAdministracao.get().getNome(), "",
		 * (Data)'1999-05-08');
		 */

		return ResponseEntity.ok().build();
	}

}
