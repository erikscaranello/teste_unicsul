package br.com.unicsul.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.unicsul.dto.NaveDTO;
import br.com.unicsul.service.NaveService;
import br.com.unicsul.serviceImpl.NaveServiceImpl;

@ManagedBean
public class IndexBean {
	
	
	public List<NaveDTO> getNames() throws IOException {
		
		NaveService naveService = new NaveServiceImpl();
		List<NaveDTO> listaDeNaves = naveService.obterListaDeNaves();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("listaDeNaves", listaDeNaves);

		return listaDeNaves;
	}

}
