package br.com.Calculadora.orm;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Embeddable
public class DiluicaoConfiguracaoPK implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Medicamento medicamento;

	@ManyToOne(fetch = FetchType.LAZY)
	private ViaAdministracao viaAdministracao;

	private BigInteger sequencia;

	public DiluicaoConfiguracaoPK(Medicamento medicamento, ViaAdministracao viaAdministracao, BigInteger sequencia) {
		this.medicamento = medicamento;
		this.viaAdministracao = viaAdministracao;
		this.sequencia = sequencia;
	}

	public DiluicaoConfiguracaoPK() {
		// TODO Auto-generated constructor stub
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public ViaAdministracao getViaAdministracao() {
		return viaAdministracao;
	}

	public BigInteger getSequencia() {
		return sequencia;
	}

}
