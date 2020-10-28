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

import br.com.doaju.dto.DoadorDTO;
import br.com.doaju.request.DoadorRequest;
import br.com.doaju.service.DoadorService;

@CrossOrigin
@RestController
@RequestMapping("/doadores")
public class DoadorController {
	@Autowired
	private DoadorService service;
	
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody DoadorRequest doadorRequest) {	
		try {
			
			DoadorDTO doadorDTO = service.salvar(doadorRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(doadorDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
		
	@GetMapping
	public List<DoadorDTO> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		DoadorDTO doador = service.buscar(id);
		
		if (doador != null) {
			return ResponseEntity.ok(doador);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody DoadorRequest doadorRequest, @PathVariable Long id) {
		
		DoadorDTO doadorAtual = service.buscar(id);
		
		if (doadorAtual != null) {
			BeanUtils.copyProperties(doadorRequest, doadorAtual, "id");
			
			service.atualizar(doadorRequest);
			return ResponseEntity.ok(doadorRequest);
		}	
			
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}

}
