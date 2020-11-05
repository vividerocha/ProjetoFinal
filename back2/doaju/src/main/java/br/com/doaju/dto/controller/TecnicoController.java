package br.com.doaju.dto.controller;

import java.util.List;

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

import br.com.doaju.dto.TecnicoDTO;
import br.com.doaju.request.TecnicoRequest;
import br.com.doaju.service.TecnicoService;

@CrossOrigin
@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {
	@Autowired
	private TecnicoService service;
	
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody TecnicoRequest tecnicoRequest) {	
		try {
			System.out.println(tecnicoRequest.toString());
			TecnicoDTO tecnicoDTO = service.salvar(tecnicoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoDTO);
		
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
		
	@GetMapping
	public List<TecnicoDTO> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		TecnicoDTO tecnico = service.buscar(id);
		
		if (tecnico != null) {
			return ResponseEntity.ok(tecnico);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<?> buscarPorIdUser(@PathVariable Long id) {
		
		TecnicoDTO tecnico = service.buscarPoridUser(id);
		
		if (tecnico != null) {
			return ResponseEntity.ok(tecnico);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody TecnicoRequest tecnicoRequest, @PathVariable Long id) {
		
		TecnicoDTO tecnicoAtual = service.buscar(id);		
		
		if (tecnicoAtual != null) {
			BeanUtils.copyProperties(tecnicoRequest, tecnicoAtual, "id");			
			service.atualizar(tecnicoRequest);
			return ResponseEntity.ok(tecnicoRequest);
		}	
			
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}

}
