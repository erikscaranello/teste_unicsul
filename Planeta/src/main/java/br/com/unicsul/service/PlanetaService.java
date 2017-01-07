package br.com.unicsul.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.unicsul.dto.PlanetaDTO;

public interface PlanetaService {

	List<PlanetaDTO> obterPlanetas() throws JsonParseException, JsonMappingException, IOException;

}
