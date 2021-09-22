package br.com.Calculadora.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.Calculadora.Dto.CalculoDto;
import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Exceptions.DuplicateValueException;
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

	@Autowired
	public CalculoHistoricoService(HistoricoRepository historicoRepository,
			DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository, MedicamentoRepository medicamentoRepository,
			ViaAdministracaoRepository viaAdministracaoRepository) {
		this.historicoRepository = historicoRepository;
		this.diluicaoConfiguracaoRepository = diluicaoConfiguracaoRepository;
		this.medicamentoRepository = medicamentoRepository;
		this.viaAdministracaoRepository = viaAdministracaoRepository;
	}

	public ResponseEntity<List<HistoricoDto>> lista(BigInteger id, Date dataInicio, Date dataFim) {// localdate
		List<HistoricoDto> historicoDtoList = new ArrayList<HistoricoDto>();
		// LocalDate dataInicial = LocalDate.parse(dataInicio, formatter);
		// LocalDate dataFinal = LocalDate.parse(dataFim, formatter);
		Medicamento medicamento = medicamentoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Medicamento com id = " + id));
		String nomeMedicamento = medicamento.getNome();
		List<Historico> historico = historicoRepository.findByNomeMedicamentoAndDataCalculoBetween(nomeMedicamento, dataInicio, dataFim);
		historico.forEach(his -> {
			historicoDtoList.add(new HistoricoDto(his));
		});
		return new ResponseEntity<>(historicoDtoList, HttpStatus.OK);
	}

	public ResponseEntity<CalculoDto> criar(CalculoForm calculoForm) {
		BigInteger idMedicamento = calculoForm.getIdMedicamento();
		BigInteger idViaAdministracao = calculoForm.getIdViaAdministracao();
		Date data = Date.valueOf(LocalDate.now());
		String resultadoJson = "";
		BigDecimal resultado = BigDecimal.ZERO;

		Medicamento medicamento = medicamentoRepository.findById(idMedicamento)
				.orElseThrow(() -> new RecordNotFoundException("Não encontrado Medicamento com id = " + idMedicamento));

		ViaAdministracao viaAdministracao = viaAdministracaoRepository.findById(idViaAdministracao)
				.orElseThrow(() -> new RecordNotFoundException(
						"Não encontrado Via de Administração com o id = " + idViaAdministracao));

		resultado = calculoForm.getPrescricao().divide(medicamento.getQuantidadeApresentacao(),
				MathContext.DECIMAL64);

		List<DiluicaoConfiguracao> diluicaoConfiguracaoList = diluicaoConfiguracaoRepository
			.findByDiluicaoConfiguracaoPKMedicamentoIdAndDiluicaoConfiguracaoPKViaAdministracaoId(idMedicamento, idViaAdministracao);
		System.out.println("4444444");

		List<String> passosAdministracao = new ArrayList<>();
		if (!diluicaoConfiguracaoList.isEmpty()) {
			resultadoJson.concat("\nPassos: ");
			diluicaoConfiguracaoList.forEach(diluicao -> {
				resultadoJson.concat("Sequencia " + diluicao.getSequencia() + ": " + diluicao.getModoPreparo() + "\n");
				passosAdministracao.add(diluicao.getModoPreparo());
			});
		}

		List<String> informacoes = new ArrayList<>();
		if (medicamento.getInfoObservacao() != "") {
			informacoes.add(medicamento.getInfoObservacao());
		}
		if (medicamento.getInfoTempoAdministracao() != "") {
			informacoes.add(medicamento.getInfoTempoAdministracao());
		}
		if (medicamento.getInfoSobra() != "") {
			informacoes.add(medicamento.getInfoSobra());
		}

		Historico historico = new Historico(medicamento.getId(), calculoForm.getNomeUsuario(), medicamento.getNome(),
				String.valueOf(medicamento.getQuantidadeApresentacao()), String.valueOf(calculoForm.getPrescricao()),
				viaAdministracao.getNome(), resultadoJson, data);

		try {
			historicoRepository.save(historico);
		} catch (RuntimeException e) {
			throw new DuplicateValueException("Não foi possível armazenar o calculo no Histórico");
		}

		CalculoDto calculoDto = new CalculoDto(String.valueOf(resultado), passosAdministracao, informacoes);
		return new ResponseEntity<>(calculoDto, HttpStatus.OK);
	}

}
