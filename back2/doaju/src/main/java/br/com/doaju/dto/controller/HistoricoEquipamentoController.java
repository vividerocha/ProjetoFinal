package br.com.doaju.dto.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doaju.dto.HistoricoEquipamentoDTO;
import br.com.doaju.model.Equipamento;
import br.com.doaju.model.HistoricoEquipamento;
import br.com.doaju.request.HistoricoEquipamentoRequest;
import br.com.doaju.service.HistoricoEquipamentoService;

@CrossOrigin
@RestController
@RequestMapping("/historicoEquipamento")
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Equipamento equipamento) {
		
		Optional<HistoricoEquipamento> historicoEquipamento = service.buscar(equipamento);
		
		if (historicoEquipamento.isPresent()) {
			return ResponseEntity.ok(historicoEquipamento.get());
		}
		
		return ResponseEntity.notFound().build();
	
	}

}
