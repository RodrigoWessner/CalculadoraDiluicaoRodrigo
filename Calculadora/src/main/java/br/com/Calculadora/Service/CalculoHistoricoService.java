package br.com.Calculadora.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Form.CalculoForm;
import br.com.Calculadora.Repository.DiluicaoConfiguracaoRepository;
import br.com.Calculadora.Repository.HistoricoRepository;
import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.Historico;

@Service
public class CalculoHistoricoService {

	HistoricoRepository historicoRepository;
	DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository;

	@Autowired
	public CalculoHistoricoService(HistoricoRepository historicoRepository,
			DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository) {
		this.historicoRepository = historicoRepository;
		this.diluicaoConfiguracaoRepository = diluicaoConfiguracaoRepository;
	}

	// metodos
	public ResponseEntity<List<HistoricoDto>> lista() {
		List<Historico> historico = historicoRepository.findAll();
		List<HistoricoDto> historicoDtoList = new ArrayList<HistoricoDto>();

		historico.forEach(hist -> {
			historicoDtoList.add(new HistoricoDto(hist));
		});

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
