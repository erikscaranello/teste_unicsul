package br.com.unicsul.dto;

import java.util.List;

public class PlanoDeVooDTO {
	
	private NaveDTO nave;
	
	private List<PlanetaDTO> planetas;

	public NaveDTO getNave() {
		return nave;
	}

	public List<PlanetaDTO> getPlanetas() {
		return planetas;
	}

	public void setNave(NaveDTO nave) {
		this.nave = nave;
	}

	public void setPlanetas(List<PlanetaDTO> planetas) {
		this.planetas = planetas;
	}
}
