package br.com.unicsul.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.unicsul.dto.NaveDTO;
import br.com.unicsul.dto.PlanetaDTO;
import br.com.unicsul.dto.PlanoDeVooDTO;
import br.com.unicsul.service.NaveService;
import br.com.unicsul.service.PlanetaService;
import br.com.unicsul.service.PlanoDeVooService;

public class PlanoDeVooServiceImpl implements PlanoDeVooService {

	private NaveService naveService = new NaveServiceImpl();
	
	private PlanetaService planetaService = new PlanetaServiceImpl();
	
	
	@SuppressWarnings("unchecked")
	public String inserirPlanetaNoPlanoDeVoo(FacesContext currentInstance, String params) throws JsonParseException, JsonMappingException, IOException {
		
		Integer idNave = null;
		Integer idPlaneta = null;
		if(params.indexOf(";") > -1) {
			String[] arrayParams = params.split(";");
			
			idNave = Integer.parseInt(arrayParams[0]);
			idPlaneta = Integer.parseInt(arrayParams[1]);
			
		} else {
			idNave = Integer.parseInt(params);
		}
		
		if(idPlaneta == null) {
			return "";
		
		} else {
			HttpSession session = (HttpSession) currentInstance.getExternalContext().getSession(true);
			List<PlanoDeVooDTO> listaDePlanosDeVoos = (List<PlanoDeVooDTO>) session.getAttribute("planosDeVoos");
		
			if(listaDePlanosDeVoos == null || (listaDePlanosDeVoos != null && listaDePlanosDeVoos.isEmpty())) {
				listaDePlanosDeVoos = new ArrayList<PlanoDeVooDTO>();
				
				
				PlanoDeVooDTO planoDevooDto = this.adicionaPlanoDeVoo(session, idNave, idPlaneta);
				
				listaDePlanosDeVoos.add(planoDevooDto);
				session.setAttribute("planosDeVoos", listaDePlanosDeVoos);
				
				return "Planeta colocado no plano de voo com sucesso!";
			
			} else {
				
				//pegar o plano de voo daquela nave

//				boolean para verificar se existe plano de voo desta nave
				Boolean existePlanoDaNave = Boolean.FALSE;
				for(PlanoDeVooDTO planoDeVoo : listaDePlanosDeVoos) {
					
					if(planoDeVoo.getNave().getId().equals(idNave)) {
						
						//verificacao do último planeta 
						
						if(planoDeVoo.getPlanetas().get(planoDeVoo.getPlanetas().size() - 1).getId().equals(idPlaneta)) {
							
							return "O planeta de destino não pode ser igual ao último planeta";
							
						} else {
							List<PlanetaDTO> listaDePlanetas = planetaService.obterPlanetas();
							
							for(PlanetaDTO planeta :listaDePlanetas) {
								if(planeta.getId().equals(idPlaneta)) {
									planoDeVoo.getPlanetas().add(planeta);
									session.setAttribute("planosDeVoos", listaDePlanosDeVoos);
									
									return "Planeta colocado no plano de voo com sucesso!";
								}
							}
						}
						
						
					} 
				}
				
				if(!existePlanoDaNave) {
					
					PlanoDeVooDTO planoDevooDto = this.adicionaPlanoDeVoo(session, idNave, idPlaneta);
					listaDePlanosDeVoos.add(planoDevooDto);
					session.setAttribute("planosDeVoos", listaDePlanosDeVoos);
					
					return "Planeta colocado no plano de voo com sucesso!";
					
				}
								
			}
		}
		
		
		
		
		return null;
	}


	@SuppressWarnings("unchecked")
	private PlanoDeVooDTO adicionaPlanoDeVoo(HttpSession session, Integer idNave, Integer idPlaneta) throws JsonParseException, JsonMappingException, IOException {
		PlanoDeVooDTO planoDevooDto = new PlanoDeVooDTO();
		List<NaveDTO> listaDeNaves = (List<NaveDTO>) session.getAttribute("listaDeNaves");
	
		NaveDTO naveDto = naveService.obterNavePorNaveLista(idNave, listaDeNaves);
		planoDevooDto.setNave(naveDto);
		
		if(planoDevooDto.getPlanetas() == null || (planoDevooDto.getPlanetas() != null && planoDevooDto.getPlanetas().isEmpty())) {
			planoDevooDto.setPlanetas(new ArrayList<PlanetaDTO>());
		}
		
		
		
		List<PlanetaDTO> listaDePlanetas = planetaService.obterPlanetas();
		
		for(PlanetaDTO planeta: listaDePlanetas) {
			if(planeta.getId().equals(idPlaneta)) {
				planoDevooDto.getPlanetas().add(planeta);
			}
		}
		
		return planoDevooDto;
		
	}


	public PlanoDeVooDTO obterPlanoDeVooPorNave(Integer idNave, List<PlanoDeVooDTO> listaDePlanosDeVoos) {
		
		for(PlanoDeVooDTO planoDeVoo : listaDePlanosDeVoos) {
			if(planoDeVoo.getNave().getId().equals(idNave)) {
				return planoDeVoo;
			}
		}
		
		return null;
	}

}
