package br.com.doaju.dto.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.doaju.dto.UsuarioDTO;
import br.com.doaju.dto.controller.swagger.UsuarioControllerSwagger;
import br.com.doaju.model.Aluno;
import br.com.doaju.model.Doador;
import br.com.doaju.model.Grupo;
import br.com.doaju.model.Tecnico;
import br.com.doaju.model.Usuario;
import br.com.doaju.repository.AlunoRepository;
import br.com.doaju.repository.DoadorRepository;
import br.com.doaju.repository.TecnicoRepository;
import br.com.doaju.request.UsuarioRequest;
import br.com.doaju.service.UsuarioService;

@CrossOrigin
@RequestMapping("/usuarios")
@RestController
public class UsuarioController implements UsuarioControllerSwagger {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DoadorRepository repoDoador;
	@Autowired
	private TecnicoRepository repoTecnico;
	@Autowired
	private AlunoRepository repoAluno;

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
	@GetMapping
	public List<Usuario> listar(){
		return usuarioService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		Optional<Usuario> usuario = usuarioService.buscarPorId(id);
		
		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> excluir(@PathVariable Long id) {
		try {
			usuarioService.excluir(id);	
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody UsuarioRequest usuarioRequest, @PathVariable Long id) {
		
		Usuario usuarioAtual = usuarioService.buscarPorId(id).orElse(null);
		
		if (usuarioAtual != null) {			
			BeanUtils.copyProperties(usuarioRequest, usuarioAtual, "id");			
			usuarioService.atualizar(usuarioRequest);
			return ResponseEntity.ok(usuarioRequest);
		}	
			
		return ResponseEntity.notFound().build();
	}
	

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
	
	@GetMapping("/rec/{dadosUsuario}")
	public ResponseEntity<?> recupera(@PathVariable String dadosUsuario) {
		String[] dados = dadosUsuario.split("&");
		String email = dados[0].toString();
		String nome = dados[1].toString();
		String nomeBanco = null;		

		Usuario usuario = usuarioService.buscarEmail(email);
		Long id = null;
		if(usuario != null) {
			id =usuario.getId();
		}else {
			return ResponseEntity.badRequest().body("Email não cadastrado na nossa base de dados");
		}		
		Aluno aluno = repoAluno.buscarPorIdUsuario(id);
		if(aluno != null) {
			nomeBanco = aluno.getNomeCompleto();
			if (nome.equals(nomeBanco)) {			
				return ResponseEntity.ok(usuario);
			}else {			
				return ResponseEntity.badRequest().body("Nome não confere - doa");
			}
		}
		
		Doador doador = repoDoador.buscarPorIdUsuario(id);
		if(doador != null) {
			nomeBanco = doador.getNomeCompleto();
			if (nome.equals(nomeBanco)) {			
				return ResponseEntity.ok(usuario);
			}else {			
				return ResponseEntity.badRequest().body("Nome não confere - doa");
			}
		}
		Tecnico tecnico= repoTecnico.buscarPorIdUsuario(id);
		if(tecnico != null) {
			nomeBanco = tecnico.getNomeCompleto();
			if (nome.equals(nomeBanco)) {			
				return ResponseEntity.ok(usuario);
			}else {			
				return ResponseEntity.badRequest().body("Nome não confere - tec");
			}
		}		
		return ResponseEntity.ok(usuario);
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
	
	@GetMapping("/desc/{desc}")
	public ResponseEntity<?> buscaIdGrupoAdmin(@PathVariable String desc) {

		//Long idGrupo = usuarioService.buscarIdGrupoAdmin(desc);
		Grupo grupo = usuarioService.buscarIdGrupoAdmin(desc);
		if (desc != "") {
			if (grupo != null) {
				return ResponseEntity.ok(grupo);
			} else {
				// return ResponseEntity.notFound().build();
				return ResponseEntity.badRequest().body("Não existe um Grupo de Admin cadastrado no banco!");
			}

		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
