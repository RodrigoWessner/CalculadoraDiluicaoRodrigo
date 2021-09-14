package br.com.Calculadora.orm;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class GrupoMedicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	private BigInteger id;
	
	@Column(unique = true, nullable = false)
	private String nome;

	public GrupoMedicamento(BigInteger id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public GrupoMedicamento(String nome) {
		this.nome = nome;
	}
	
	public GrupoMedicamento() {
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getId() + getNome();
	}

}
