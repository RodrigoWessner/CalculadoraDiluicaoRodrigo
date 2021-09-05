package br.com.Calculadora.Repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, BigInteger>{

}
