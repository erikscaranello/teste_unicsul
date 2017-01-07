package br.com.unicsul.service;

import java.io.IOException;
import java.util.List;

import br.com.unicsul.dto.PessoaDTO;

public interface UsuarioService {

	List<PessoaDTO> obterListaDeUsuarios() throws IOException;

}
