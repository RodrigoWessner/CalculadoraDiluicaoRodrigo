package br.com.Calculadora.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Calculadora.orm.DiluicaoConfiguracao;
import br.com.Calculadora.orm.DiluicaoConfiguracaoPK;

@Repository
public interface DiluicaoConfiguracaoRepository extends JpaRepository<DiluicaoConfiguracao, DiluicaoConfiguracaoPK>{
}
