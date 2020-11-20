package br.com.doaju.dto.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.model.Usuario;
import br.com.doaju.request.UsuarioRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Controller de Usuário")

public interface UsuarioControllerSwagger {

	@ApiOperation("Lista um usuário")
	public List<Usuario> listar();
	
	@ApiOperation("Salva um usuário")
	public ResponseEntity<?> salvar(@RequestBody UsuarioRequest usuario);
	
	@ApiOperation("Busca um usuário por id")
	public ResponseEntity<?> buscar(@PathVariable Long id);
	
	@ApiOperation("Atualiza um usuário")
	public ResponseEntity<?> atualizar(@RequestBody UsuarioRequest usuarioRequest, @PathVariable Long id);
	
	@ApiOperation("Exclui um usuário")
	public ResponseEntity<Usuario> excluir(@PathVariable Long id);
	
	@ApiOperation("Busca grupo Administrador")
	public ResponseEntity<?> buscaIdGrupoAdmin(@PathVariable String desc);
	
	@ApiOperation("Busca um usuário por email")
	public ResponseEntity<?> buscarEmail(@PathVariable String email);
	
	@ApiOperation("Busca um usuário por login")
	public ResponseEntity<?> login(@PathVariable String dadosUsuario);
	
	@ApiOperation("Recupera os dados do usuário")
	public ResponseEntity<?> recupera(@PathVariable String dadosUsuario);
	
	@ApiOperation("Busca o usuário")
	public ResponseEntity<?> buscarUsuario(@PathVariable String user);
}
