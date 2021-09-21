package br.com.Calculadora.Repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.ViaAdministracao;

@Repository
public interface ViaAdministracaoRepository extends JpaRepository<ViaAdministracao, BigInteger>{

	Optional<ViaAdministracao> findByNome(String nomeViaAdministracao);

	
}
