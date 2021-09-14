package br.com.Calculadora.Repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, BigInteger>{

	List<Medicamento> findByNome(String nome);

}
