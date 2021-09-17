package br.com.Calculadora.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, BigInteger> {
	@Query(value = "select * from historico "
			+ "where nome_medicamento = :nome "
			+ "and data_calculo between :dataInicio and :dataFim", nativeQuery = true)
	List<Historico> findByNomeAndBetweenDatas(String nome, LocalDate dataInicio, LocalDate dataFim);
}
