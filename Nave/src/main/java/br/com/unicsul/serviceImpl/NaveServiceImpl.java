package br.com.unicsul.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

import br.com.unicsul.dto.NaveDTO;
import br.com.unicsul.service.NaveService;

public class NaveServiceImpl implements NaveService{

	List<NaveDTO> listaDeNavesRetornadas = new ArrayList<NaveDTO>();
	
	public List<NaveDTO> obterListaDeNaves() throws JsonParseException, JsonMappingException, IOException {
		
		this.obterJson(null);
		
		return listaDeNavesRetornadas;
	}

	
	
	private void obterJson(Integer numeroDaPagina) throws JsonParseException, JsonMappingException, JSONException, IOException {
		
		URL urlPeople = null;
		if(numeroDaPagina != null) {
			urlPeople = new URL("http://swapi.co/api/starships/?page=" + numeroDaPagina);
		} else {
			urlPeople = new URL("http://swapi.co/api/starships/");
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
				NaveDTO naveDto = mapper.readValue(results.get(i).toString(), NaveDTO.class);
				naveDto.setId(i + 1);
				listaDeNavesRetornadas.add(naveDto);
			}
			
			
			if(retorno.has("next") && !retorno.isNull("next")) {
				String next = retorno.getString("next");
				
				next = next.substring(next.length() - 1, next.length());
				
				this.obterJson(Integer.parseInt(next));
			}
			
			
		}
		
		
	}



	public NaveDTO obterNavePorNaveLista(String params, List<NaveDTO> listaDeNaves) {
		Integer idNave = null;
		if(params.indexOf(";") > -1) {
			String[] arrayParams = params.split(";");
			idNave = Integer.parseInt(arrayParams[0]);
		} else {
			idNave = Integer.parseInt(params);
		}
		
		
		for(NaveDTO nave : listaDeNaves) {
			if(nave.getId().equals(idNave)) {
				return nave;
			}
		}
		
		return null;
	}
	
	
	public NaveDTO obterNavePorNaveLista(Integer idNave, List<NaveDTO> listaDeNaves) {
		for(NaveDTO nave : listaDeNaves) {
			if(nave.getId().equals(idNave)) {
				return nave;
			}
		}
		
		return null;
	}
}
