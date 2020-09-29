package br.com.digitalhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalhouse.dto.UsuarioDTO;
import br.com.digitalhouse.model.Usuario;
import br.com.digitalhouse.service.UsuarioService;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO dto) {
	    Usuario usuario = usuarioService.salvar(dto.transformaParaObjeto());
	    return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}

}
