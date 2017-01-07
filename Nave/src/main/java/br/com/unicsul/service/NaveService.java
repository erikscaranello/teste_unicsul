package br.com.unicsul.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.unicsul.dto.NaveDTO;

public interface NaveService {

	List<NaveDTO> obterListaDeNaves() throws JsonParseException, JsonMappingException, IOException;

	NaveDTO obterNavePorNaveLista(String parametros, List<NaveDTO> listaDeNaves);

	NaveDTO obterNavePorNaveLista(Integer idNave, List<NaveDTO> listaDeNaves);
}
