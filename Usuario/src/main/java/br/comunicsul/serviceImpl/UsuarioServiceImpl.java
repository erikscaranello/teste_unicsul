package br.comunicsul.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.unicsul.dto.PessoaDTO;
import br.com.unicsul.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	@Override
	public List<PessoaDTO> obterListaDeUsuarios() throws IOException {
		
		List<PessoaDTO> listaDePessoasRetornadas = new ArrayList<PessoaDTO>();
		
		Boolean existemPessoas = Boolean.TRUE;
		Integer numerodaPessoa = 1;
		while (existemPessoas) {
			
			URL urlPeople = new URL("http://swapi.co/api/people/" + numerodaPessoa);
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
				ObjectMapper mapper = new ObjectMapper();
				PessoaDTO pessoaDto = mapper.readValue(retorno.toString(), PessoaDTO.class);
				pessoaDto.setId(numerodaPessoa);
				
				
				listaDePessoasRetornadas.add(pessoaDto);
				numerodaPessoa++;
			} else {
				existemPessoas = Boolean.FALSE;
			}
			
		}
		
		return listaDePessoasRetornadas;
	}

}
