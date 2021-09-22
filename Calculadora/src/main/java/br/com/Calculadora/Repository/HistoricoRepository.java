package br.com.Calculadora.Repository;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, BigInteger> {
	List<Historico> findByNomeMedicamentoAndDataCalculoBetween(String nome, Date dataInicio, Date dataFim);
}
