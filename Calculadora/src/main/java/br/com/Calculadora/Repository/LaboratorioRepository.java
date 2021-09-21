package br.com.Calculadora.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, BigInteger>{

	Optional<Laboratorio> findByNome(String nomeLaboratorio);
	List<Laboratorio> findAll();

	
}
