package br.com.unicsul.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.unicsul.dto.PlanetaDTO;
import br.com.unicsul.service.PlanetaService;

public class PlanetaServiceImpl implements PlanetaService {

	List<PlanetaDTO> listaDePlanetasRetornados = new ArrayList<PlanetaDTO>();
	
	public List<PlanetaDTO> obterPlanetas() throws JsonParseException, JsonMappingException, IOException {
		
		this.obterJson(null);
		
		return listaDePlanetasRetornados;
	}

	
	
	private void obterJson(Integer numeroDaPagina) throws JsonParseException, JsonMappingException, JSONException, IOException {
		
		URL urlPeople = null;
		if(numeroDaPagina != null) {
			urlPeople = new URL("http://swapi.co/api/planets/?page=" + numeroDaPagina);
		} else {
			urlPeople = new URL("http://swapi.co/api/planets/");
		}
		
		
		HttpURLConnection connection = (HttpURLConnection) urlPeople.openConnection();            
		
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
		connection.addRequestProperty("Request-Method","GET");      
		connection.setRequestProperty("accept", "application/json");
		  
		connection.connect();    
		       
		JSONObject retorno = null;
		if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {    
		    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));    
		    String newData = "";    
		    String s = "";    
		    while (null != ((s = br.readLine()))) {    
		        newData += s;    
		    }    
		    br.close();    
		    
		    retorno = new JSONObject(newData);
		} 
		
		if(retorno != null) {
			
			JSONArray results = retorno.getJSONArray("results");
			
			
			for(Integer i = 0; i < results.length(); i++) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				PlanetaDTO planetaDto = mapper.readValue(results.get(i).toString(), PlanetaDTO.class);
				planetaDto.setId(i + 1);
				listaDePlanetasRetornados.add(planetaDto);
			}
			
			
			if(retorno.has("next") && !retorno.isNull("next")) {
				String next = retorno.getString("next");
				
				next = next.substring(next.length() - 1, next.length());
				
				this.obterJson(Integer.parseInt(next));
			}
			
			
		}
		
		
	}

}
