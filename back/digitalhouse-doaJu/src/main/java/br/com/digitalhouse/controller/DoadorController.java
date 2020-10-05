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



import br.com.digitalhouse.dto.DoadorDTO;
import br.com.digitalhouse.model.Doador;
import br.com.digitalhouse.service.DoadorService;

@CrossOrigin
@RequestMapping("/doadores")
@RestController
public class DoadorController {

	private final DoadorService doadorService;

	@Autowired
	public DoadorController(DoadorService doadorService) {
		this.doadorService = doadorService;
	}
	
	@PostMapping
	public ResponseEntity<Doador> salvar(@RequestBody DoadorDTO dto) {
	    Doador doador = doadorService.salvar(dto.transformaParaObjeto());
	    return new ResponseEntity<>(doador, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Doador> listar(){
		return doadorService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Doador> buscar(@PathVariable Long id) {
		
		Optional<Doador> doador = doadorService.buscar(id);
		
		if (doador.isPresent()) {
			return ResponseEntity.ok(doador.get());
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Doador> excluir(@PathVariable Long id) {
		try {
			doadorService.excluir(id);	
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
			
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody @Valid Doador doador, @PathVariable Long id) {
		
		Doador doadorAtual = doadorService.buscar(id).orElse(null);
		
		if (doadorAtual != null) {
			BeanUtils.copyProperties(doador, doadorAtual, "id");
			
			doadorService.atualizar(doadorAtual);
			return ResponseEntity.ok(doadorAtual);
		}	
			
		return ResponseEntity.notFound().build();
	}

}
