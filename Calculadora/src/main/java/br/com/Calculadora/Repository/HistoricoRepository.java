package br.com.Calculadora.Repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, BigInteger>{

}
