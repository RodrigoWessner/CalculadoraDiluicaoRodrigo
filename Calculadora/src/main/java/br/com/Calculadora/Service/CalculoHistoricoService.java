package br.com.Calculadora.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.Calculadora.Dto.CalculoDto;
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

	public ResponseEntity<CalculoDto> criar(CalculoForm calculoForm) {
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

		BigDecimal resultado = calculoForm.getPrescricao().divide(medicamento.get().getQuantidadeApresentacao(),
				MathContext.DECIMAL64);

		Date data = Date.valueOf(LocalDate.now());
		
		Historico historico = new Historico(medicamento.get().getId(), calculoForm.getNomeUsuario(),
				medicamento.get().getNome(), String.valueOf(medicamento.get().getQuantidadeApresentacao()),
				String.valueOf(calculoForm.getPrescricao()), viaAdministracao.get().getNome(), "", data);
		historicoRepository.save(historico);

		List<DiluicaoConfiguracao> diluicaoConfiguracaoList = diluicaoConfiguracaoRepository
				.findDiluicaoConfiguracaoIdViaIdMed(idMedicamento, idViaAdministracao);
		List<String> passosAdministracao = new ArrayList<>();
		if (!diluicaoConfiguracaoList.isEmpty()) {
			diluicaoConfiguracaoList.forEach(diluicao -> {
				passosAdministracao.add(diluicao.getModoPreparo());
			});
		}

		List<String> informacoes = new ArrayList<>();
		if (medicamento.get().getInfoObservacao() != "") {
			informacoes.add(medicamento.get().getInfoObservacao());
		}
		if (medicamento.get().getInfoTempoAdministracao() != "") {
			informacoes.add(medicamento.get().getInfoTempoAdministracao());
		}
		if (medicamento.get().getInfoSobra() != "") {
			informacoes.add(medicamento.get().getInfoSobra());
		}
		CalculoDto calculoDto = new CalculoDto(String.valueOf(resultado), passosAdministracao, informacoes);
		return new ResponseEntity<>(calculoDto, HttpStatus.OK);
	}

}
