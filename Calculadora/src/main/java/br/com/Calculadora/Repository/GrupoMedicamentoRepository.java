package br.com.Calculadora.Repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.GrupoMedicamento;

@Repository
public interface GrupoMedicamentoRepository extends JpaRepository<GrupoMedicamento, BigInteger>{

}