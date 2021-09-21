package br.com.Calculadora.Dto;

import java.util.ArrayList;
import java.util.List;

public class CalculoDto {
	private String infoAdministração;
	private List<String> passosAdministracao;
	private List<String> infoList;

	public CalculoDto(String infoAdministração, List<String> passosAdministracao, List<String> infoList) {
		this.infoAdministração = infoAdministração;
		if (!passosAdministracao.isEmpty()) {
			this.passosAdministracao = new ArrayList<>();
			passosAdministracao.forEach(passos -> {
				this.passosAdministracao.add(passos);
			});
			}
		if (!infoList.isEmpty()) {
			this.infoList = new ArrayList<>();
			infoList.forEach(info -> {
				this.infoList.add(info);
			});
		}
	}

	public CalculoDto() {
	}

	public String getInfoAdministração() {
		return infoAdministração;
	}

	public void setInfoAdministração(String infoAdministração) {
		this.infoAdministração = infoAdministração;
	}

	public List<String> getPassosAdministracao() {
		return passosAdministracao;
	}

	public void setPassosAdministracao(List<String> passosAdministracao) {
		this.passosAdministracao = passosAdministracao;
	}

	public List<String> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<String> infoList) {
		this.infoList = infoList;
	}

}
