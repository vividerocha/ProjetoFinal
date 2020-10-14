package br.com.digitalhouse.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalhouse.dto.UsuarioDTO;
import br.com.digitalhouse.model.Usuario;
import br.com.digitalhouse.request.UsuarioRequest;
import br.com.digitalhouse.service.UsuarioService;
import jdk.jfr.BooleanFlag;

@CrossOrigin
@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody UsuarioRequest usuario) {
		try {
			UsuarioDTO usuarioDTO = service.salvar(usuario);
			if (usuarioDTO == null) {
				return ResponseEntity.badRequest().body("Já existe um usuário cadastrado para esse email!");
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

//	private final UsuarioService usuarioService;
//
//	@Autowired
//	public UsuarioController(UsuarioService usuarioService) {
//		this.usuarioService = usuarioService;
//	}
//	
//	@PostMapping
//	public ResponseEntity<?> salvar(@RequestBody UsuarioDTO dto) {
//		System.out.println(dto.toString());
//		Usuario usuario = usuarioService.buscarEmail(dto.getEmail());
//		if (usuario != null)
//	    {
//			return ResponseEntity.badRequest().body("Já existe um usuário cadastrado para esse email!");
//	    }else {
//	    	usuarioService.salvar(dto.transformaParaObjeto());
//		    return ResponseEntity.ok("Usuário cadastrado com sucesso!");	
//	    }
//	}
//	@GetMapping
//	public List<Usuario> listar(){
//		return usuarioService.listar();
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
//		
//		Optional<Usuario> usuario = usuarioService.buscar(id);
//		
//		if (usuario.isPresent()) {
//			return ResponseEntity.ok(usuario.get());
//		}
//		
//		return ResponseEntity.notFound().build();
//	
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Usuario> excluir(@PathVariable Long id) {
//		try {
//			usuarioService.excluir(id);	
//			return ResponseEntity.noContent().build();
//			
//		} catch (Exception e) {
//			return ResponseEntity.notFound().build();
//		}
//			
////		} catch (Exception e) {
////			return ResponseEntity.status(HttpStatus.CONFLICT).build();
////		}
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> atualizar(@RequestBody @Valid Usuario usuario, @PathVariable Long id) {
//		
//		Usuario usuarioAtual = usuarioService.buscar(id).orElse(null);
//		
//		if (usuarioAtual != null) {
//			BeanUtils.copyProperties(usuario, usuarioAtual, "id");
//			
//			usuarioService.atualizar(usuarioAtual);
//			return ResponseEntity.ok(usuarioAtual);
//		}	
//			
//		return ResponseEntity.notFound().build();
//	}
//	
//	@GetMapping("/email/{email}")
//	public ResponseEntity<Usuario> buscarEmail(@PathVariable String email) {
//		
//		Usuario usuario = usuarioService.buscarEmail(email);
//		if(email != "") {
//			if (usuario != null) {
//				return ResponseEntity.ok(usuario);
//			}
//			
//			return ResponseEntity.notFound().build();
//		}else {
//			return ResponseEntity.notFound().build();
//		}
//	
//	}
//	

}
