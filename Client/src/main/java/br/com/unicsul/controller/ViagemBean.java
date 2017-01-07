package br.com.unicsul.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.unicsul.dto.NaveDTO;
import br.com.unicsul.dto.PlanetaDTO;
import br.com.unicsul.service.NaveService;
import br.com.unicsul.service.PlanetaService;
import br.com.unicsul.service.PlanoDeVooService;
import br.com.unicsul.serviceImpl.NaveServiceImpl;
import br.com.unicsul.serviceImpl.PlanetaServiceImpl;
import br.com.unicsul.serviceImpl.PlanoDeVooServiceImpl;

@ManagedBean
public class ViagemBean {
	
	private PlanetaService planetaService = new PlanetaServiceImpl();
	
	private NaveService naveService = new NaveServiceImpl();
	
	private PlanoDeVooService planoDeVooService = new PlanoDeVooServiceImpl();
	
	public String getMensagem() throws JsonParseException, JsonMappingException, IOException {
		
		String parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");
		String mensagemDeInsercao = planoDeVooService.inserirPlanetaNoPlanoDeVoo(FacesContext.getCurrentInstance(), parametros);
		
		return mensagemDeInsercao;
	}
	
	
	
	public NaveDTO getNave() throws JsonParseException, JsonMappingException, IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		@SuppressWarnings("unchecked")
		List<NaveDTO> listaDeNaves = (List<NaveDTO>) session.getAttribute("listaDeNaves");
		
		String parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");
		
		return naveService.obterNavePorNaveLista(parametros, listaDeNaves);
	}
	
	
	
	public List<PlanetaDTO> getPlanetas() throws JsonParseException, JsonMappingException, IOException {	
		return planetaService.obterPlanetas();
	}
}
