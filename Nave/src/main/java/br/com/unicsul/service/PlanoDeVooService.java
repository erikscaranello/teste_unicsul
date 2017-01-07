package br.com.unicsul.service;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.unicsul.dto.PlanoDeVooDTO;

public interface PlanoDeVooService {

	String inserirPlanetaNoPlanoDeVoo(FacesContext currentInstance, String params) throws JsonParseException, JsonMappingException, IOException;

	PlanoDeVooDTO obterPlanoDeVooPorNave(Integer idNave, List<PlanoDeVooDTO> listaDePlanosDeVoos);

}
