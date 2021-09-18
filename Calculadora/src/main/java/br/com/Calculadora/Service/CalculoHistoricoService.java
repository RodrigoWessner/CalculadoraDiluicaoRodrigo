package br.com.Calculadora.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Form.CalculoForm;
import br.com.Calculadora.Repository.DiluicaoConfiguracaoRepository;
import br.com.Calculadora.Repository.HistoricoRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.Repository.ViaAdministracaoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.Historico;
import br.com.Calculadora.orm.Laboratorio;
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
		// List<Historico> historico = historicoRepository.findAll();
		List<HistoricoDto> historicoDtoList = new ArrayList<HistoricoDto>();
		LocalDate dataInicial = LocalDate.parse(dataInicio, formatter);
		LocalDate dataFinal = LocalDate.parse(dataFim, formatter);
		/*
		 * historico.forEach(hist -> { historicoDtoList.add(new HistoricoDto(hist)); });
		 */
		if (id != null || id == id.ZERO) {
			Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
			String nomeMedicamento = medicamento.get().getNome();
			List<Historico> historico = historicoRepository.findByNomeAndBetweenDatas(nomeMedicamento, dataInicial,
					dataFinal);
			historico.forEach(his -> {
				historicoDtoList.add(new HistoricoDto(his));
			});
		}

		return ResponseEntity.ok(historicoDtoList);
	}

	public ResponseEntity<HistoricoDto> criar(CalculoForm calculoForm) {
		Optional<Medicamento> medicamento = medicamentoRepository.findById(calculoForm.getIdMedicamento());
		
		List<DiluicaoConfiguracao> diluicaoConfiguracaoList = diluicaoConfiguracaoRepository
				.findDiluicaoConfiguracaoIdViaIdMed(calculoForm.getIdMedicamento(),
						calculoForm.getIdViaAdministracao());
		
		Optional<ViaAdministracao> viaAdministracao = viaAdministracaoRepository
				.findById(calculoForm.getIdViaAdministracao());

		BigDecimal prescricao = calculoForm.getPrescricao();
		BigDecimal resultado = prescricao.divide(medicamento.get().getQuantidadeApresentacao(), MathContext.DECIMAL64);
		
		System.out.println(resultado);
		/*Historico historico = new Historico(calculoForm.getNomeUsuario(), medicamento.get().getNome(),
				medicamento.get().getQuantidadeApresentacao(), calculoForm.getPrescricao(),
				viaAdministracao.get().getNome(), "", (Data)'1999-05-08');
*/
		

		return ResponseEntity.ok().build();
	}

}
