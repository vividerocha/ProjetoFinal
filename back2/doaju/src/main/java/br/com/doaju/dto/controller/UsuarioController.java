package br.com.doaju.dto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doaju.dto.UsuarioDTO;
import br.com.doaju.model.Usuario;
import br.com.doaju.request.UsuarioRequest;
import br.com.doaju.service.UsuarioService;

@CrossOrigin
@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody UsuarioRequest usuario) {
		try {
			UsuarioDTO usuarioDTO = usuarioService.salvar(usuario);
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
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		Optional<Usuario> usuario = usuarioService.buscarPorId(id);
		
		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	
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

	@GetMapping("/login/{dadosUsuario}")
	public ResponseEntity<?> login(@PathVariable String dadosUsuario) {
		String[] dados = dadosUsuario.split("&");
		String email = dados[0].toString();
		String senha = dados[1].toString();

		Usuario usuario = usuarioService.login(email, senha);

		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			// return ResponseEntity.notFound().build();
			return ResponseEntity.badRequest().body("Email ou Senha inválido!");
		}

	}
	@GetMapping("/user/{user}")
	public ResponseEntity<?> buscarUsuario(@PathVariable String user) {
		Usuario usuario = usuarioService.buscarUsuario(user);
		System.out.println(user);
		if(user != "") {
			if(usuario == null) {
				return ResponseEntity.ok(usuario);
			}
			return ResponseEntity.badRequest().body("Existe um usuário cadastrado para esse email!");
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> buscarEmail(@PathVariable String email) {

		Usuario usuario = usuarioService.buscarEmail(email);
		if (email != "") {
			if (usuario != null) {
				return ResponseEntity.ok(usuario);
			} else {
				// return ResponseEntity.notFound().build();
				return ResponseEntity.badRequest().body("Não existe um usuário cadastrado para esse email!");
			}

		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
