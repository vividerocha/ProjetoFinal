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

import br.com.digitalhouse.dto.TecnicoDTO;
import br.com.digitalhouse.model.Tecnico;
import br.com.digitalhouse.service.TecnicoService;

@CrossOrigin
@RequestMapping("/tecnicos")
@RestController
public class TecnicoController {

	private final TecnicoService tecnicoService;

	@Autowired
	public TecnicoController(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}
	
	@PostMapping
	public ResponseEntity<Tecnico> salvar(@RequestBody TecnicoDTO dto) {
	    Tecnico tecnico = tecnicoService.salvar(dto.transformaParaObjeto());
	    return new ResponseEntity<>(tecnico, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Tecnico> listar(){
		return tecnicoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tecnico> buscar(@PathVariable Long id) {
		
		Optional<Tecnico> tecnico = tecnicoService.buscar(id);
		
		if (tecnico.isPresent()) {
			return ResponseEntity.ok(tecnico.get());
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Tecnico> excluir(@PathVariable Long id) {
		try {
			tecnicoService.excluir(id);	
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
			
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody @Valid Tecnico tecnico, @PathVariable Long id) {
		
		Tecnico tecnicoAtual = tecnicoService.buscar(id).orElse(null);
		
		if (tecnicoAtual != null) {
			BeanUtils.copyProperties(tecnico, tecnicoAtual, "id");
			
			tecnicoService.atualizar(tecnicoAtual);
			return ResponseEntity.ok(tecnicoAtual);
		}	
			
		return ResponseEntity.notFound().build();
	}

}
