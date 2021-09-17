package br.com.Calculadora.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Form.CalculoForm;
import br.com.Calculadora.Repository.DiluicaoConfiguracaoRepository;
import br.com.Calculadora.Repository.HistoricoRepository;
import br.com.Calculadora.Repository.MedicamentoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.Historico;
import br.com.Calculadora.orm.Medicamento;

@Service
public class CalculoHistoricoService {

	HistoricoRepository historicoRepository;
	DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository;
	MedicamentoRepository medicamentoRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	public CalculoHistoricoService(HistoricoRepository historicoRepository,
			DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository,
			MedicamentoRepository medicamentoRepository) {
		this.historicoRepository = historicoRepository;
		this.diluicaoConfiguracaoRepository = diluicaoConfiguracaoRepository;
		this.medicamentoRepository = medicamentoRepository;
	}

	// metodos
	public ResponseEntity<List<HistoricoDto>> lista(BigInteger id, String dataInicio, String dataFim) {
		//List<Historico> historico = historicoRepository.findAll();
		List<HistoricoDto> historicoDtoList = new ArrayList<HistoricoDto>();
		LocalDate dataInicial = LocalDate.parse(dataInicio,formatter);
		LocalDate dataFinal = LocalDate.parse(dataFim,formatter);
		/*	
		historico.forEach(hist -> {
			historicoDtoList.add(new HistoricoDto(hist));
		});*/
		if(id != null || id == id.ZERO) {
			Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
			String nomeMedicamento = medicamento.get().getNome();
			List<Historico> historico = historicoRepository.findByNomeAndBetweenDatas(nomeMedicamento, dataInicial, dataFinal);
			historico.forEach(his ->{
				historicoDtoList.add(new HistoricoDto(his));
			});
		}	
		
		return ResponseEntity.ok(historicoDtoList);
	}

	public ResponseEntity<HistoricoDto> criar(CalculoForm calculoForm) {
		/*
		 * private BigInteger idMedicamento; private BigInteger IdViaAdministracao;
		 * private BigDecimal prescricao; private String nomeUsuario;
		 */
		List<DiluicaoConfiguracao> diluicaoConfiguracaoList = diluicaoConfiguracaoRepository.findDiluicaoConfiguracaoIdViaIdMed(calculoForm.getIdMedicamento(), calculoForm.getIdViaAdministracao());
		diluicaoConfiguracaoList.forEach(d ->{
			System.out.println(d.getViaAdministracao().getNome());
		});

		return ResponseEntity.ok().build();
	}

}
