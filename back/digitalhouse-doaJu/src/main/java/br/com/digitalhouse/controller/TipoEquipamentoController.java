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

import br.com.digitalhouse.dto.TipoEquipamentoDTO;
import br.com.digitalhouse.request.TipoEquipamentoRequest;
import br.com.digitalhouse.service.TipoEquipamentoService;

@CrossOrigin
@RestController
@RequestMapping("/tipoEquipamentos")
public class TipoEquipamentoController {
	@Autowired
	private TipoEquipamentoService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid TipoEquipamentoRequest tipoEquipamentoRequest) {	
		try {
			
			TipoEquipamentoDTO tipoEquipamentoDTO = service.salvar(tipoEquipamentoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(tipoEquipamentoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<TipoEquipamentoDTO> listar(){
		return service.listar();
	}
}
