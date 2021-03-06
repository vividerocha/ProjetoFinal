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

import br.com.digitalhouse.dto.HistoricoEquipamentoDTO;
import br.com.digitalhouse.request.HistoricoEquipamentoRequest;
import br.com.digitalhouse.service.HistoricoEquipamentoService;

@CrossOrigin
@RestController
@RequestMapping("/historicoEquipamentos")
public class HistoricoEquipamentoController {
	@Autowired
	private HistoricoEquipamentoService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid HistoricoEquipamentoRequest historicoEquipamentoRequest) {	
		try {
			
			HistoricoEquipamentoDTO historicoEquipamentoDTO = service.salvar(historicoEquipamentoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(historicoEquipamentoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<HistoricoEquipamentoDTO> listar(){
		return service.listar();
	}

}
