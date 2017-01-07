package br.com.unicsul.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.unicsul.dto.NaveDTO;
import br.com.unicsul.dto.PlanoDeVooDTO;
import br.com.unicsul.service.NaveService;
import br.com.unicsul.service.PlanoDeVooService;
import br.com.unicsul.serviceImpl.NaveServiceImpl;
import br.com.unicsul.serviceImpl.PlanoDeVooServiceImpl;

@ManagedBean
public class PlanetaPlanoDeVooBean {
	
	private NaveService naveService = new NaveServiceImpl();
	
	private PlanoDeVooService planoDeVooService = new PlanoDeVooServiceImpl();
	
	@SuppressWarnings("unchecked")
	public NaveDTO getNave() {
		Integer idNave = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nave"));
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		List<NaveDTO> listaDeNaves = (List<NaveDTO>) session.getAttribute("listaDeNaves");
		
		return naveService.obterNavePorNaveLista(idNave, listaDeNaves);
	}
	
	
	@SuppressWarnings("unchecked")
	public PlanoDeVooDTO getPlanoDeVoo() {
		Integer idNave = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nave"));
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		List<PlanoDeVooDTO> listaDePlanosDeVoos = (List<PlanoDeVooDTO>) session.getAttribute("planosDeVoos");
		
		
		return planoDeVooService.obterPlanoDeVooPorNave(idNave, listaDePlanosDeVoos);
	}
}
