package br.com.doaju.dto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doaju.dto.EquipamentoDTO;
import br.com.doaju.request.EquipamentoRequest;
import br.com.doaju.service.EquipamentoService;

@CrossOrigin
@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
	@Autowired
	private EquipamentoService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody EquipamentoRequest equipamentoRequest) {	
		try {
			
			EquipamentoDTO equipamentoDTO = service.salvar(equipamentoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	
	@GetMapping
	public List<EquipamentoDTO> listar(){
		return service.listar();
	}

}
