package br.com.Calculadora.orm;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DiluicaoConfiguracaoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "medicamento_id")
	private Medicamento medicamento;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "via_administracao_id")
	private ViaAdministracao viaAdministracao;

	private BigInteger sequencia;

	public DiluicaoConfiguracaoPK(Medicamento medicamento, ViaAdministracao viaAdministracao, BigInteger sequencia) {
		this.medicamento = medicamento;
		this.viaAdministracao = viaAdministracao;
		this.sequencia = sequencia;
	}

	public DiluicaoConfiguracaoPK() {
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public void setViaAdministracao(ViaAdministracao viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}

	public void setSequencia(BigInteger sequencia) {
		this.sequencia = sequencia;
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

	@Override
	public String toString() {
		return "DiluicaoConfiguracaoPK [medicamento=" + medicamento.getId() + ", viaAdministracao=" + viaAdministracao.getId()
				+ ", sequencia=" + sequencia + "]";
	}

}
