package br.com.Calculadora.Repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.DiluicaoConfiguracaoPK;

@Repository
public interface DiluicaoConfiguracaoRepository extends JpaRepository<DiluicaoConfiguracao, DiluicaoConfiguracaoPK> {

	List<DiluicaoConfiguracao> findByDiluicaoConfiguracaoPKMedicamentoIdAndDiluicaoConfiguracaoPKViaAdministracaoId(
			BigInteger medicamento, BigInteger viaAdministracao);

}
