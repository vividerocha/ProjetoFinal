package br.com.doaju.dto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<?> salvar(@RequestBody @Valid TecnicoRequest tecnicoRequest) {	
		try {
			
			TecnicoDTO tecnicoDTO = service.salvar(tecnicoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<TecnicoDTO> listar(){
		return service.listar();
	}

}
