package br.com.digitalhouse.controller;

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

import br.com.digitalhouse.dto.DoadorDTO;
import br.com.digitalhouse.request.DoadorRequest;
import br.com.digitalhouse.service.DoadorService;

@CrossOrigin
@RestController
@RequestMapping("/doadores")
public class DoadorController {
	@Autowired
	private DoadorService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid DoadorRequest doadorRequest) {	
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

}
