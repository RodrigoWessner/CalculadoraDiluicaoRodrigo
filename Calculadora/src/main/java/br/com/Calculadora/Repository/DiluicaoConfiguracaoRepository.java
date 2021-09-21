package br.com.Calculadora.Repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.DiluicaoConfiguracaoPK;

@Repository
public interface DiluicaoConfiguracaoRepository extends JpaRepository<DiluicaoConfiguracao, DiluicaoConfiguracaoPK>{
	@Query(value = "select * from diluicao_configuracao dc "
			+ "where dc.medicamento_id = :idMedicamento "
			+ "and dc.via_administracao_id = :idViaAdministracao "
			+ "order by sequencia",
			nativeQuery = true)
	List<DiluicaoConfiguracao> findDiluicaoConfiguracaoIdViaIdMed(BigInteger idMedicamento, BigInteger idViaAdministracao);
	
}
