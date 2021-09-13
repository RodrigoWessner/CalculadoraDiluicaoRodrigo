package br.com.Calculadora.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.Calculadora.Dto.HistoricoDto;
import br.com.Calculadora.Repository.HistoricoRepository;
import br.com.Calculadora.orm.Historico;

@Service
public class HistoricoService {

	HistoricoRepository historicoRepository;

	@Autowired
	public HistoricoService(HistoricoRepository historicoRepository) {
		this.historicoRepository = historicoRepository;
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
	
}
