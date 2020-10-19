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

import br.com.doaju.dto.SituacaoEquipamentoDTO;
import br.com.doaju.request.SituacaoEquipamentoRequest;
import br.com.doaju.service.SituacaoEquipamentoService;

@CrossOrigin
@RestController
@RequestMapping("/situacaoEquipamento")
public class SituacaoEquipamentoController {
	
	@Autowired
	private SituacaoEquipamentoService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid SituacaoEquipamentoRequest situacaoEquipamentoRequest) {	
		try {
			
			SituacaoEquipamentoDTO situacaoEquipamentoDTO = service.salvar(situacaoEquipamentoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(situacaoEquipamentoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<SituacaoEquipamentoDTO> listar(){
		return service.listar();
	}

}
