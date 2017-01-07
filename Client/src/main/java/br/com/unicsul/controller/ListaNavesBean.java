package br.com.unicsul.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.unicsul.dto.PlanoDeVooDTO;

@ManagedBean
public class ListaNavesBean {
	
	@SuppressWarnings("unchecked")
	public List<PlanoDeVooDTO> getPlanosDeVoo() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		return (List<PlanoDeVooDTO>) session.getAttribute("planosDeVoos");
		
		
	}
	
}
