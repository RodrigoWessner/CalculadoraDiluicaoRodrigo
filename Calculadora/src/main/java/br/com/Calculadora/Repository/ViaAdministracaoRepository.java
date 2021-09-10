package br.com.Calculadora.Repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.Laboratorio;
import br.com.Calculadora.orm.ViaAdministracao;

@Repository
public interface ViaAdministracaoRepository extends JpaRepository<ViaAdministracao, BigInteger>{

	ViaAdministracao findByNome(String nomeViaAdministracao);

	
}
