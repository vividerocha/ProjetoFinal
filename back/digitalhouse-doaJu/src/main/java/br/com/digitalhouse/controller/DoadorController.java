package br.com.digitalhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalhouse.dto.DoadorDTO;
import br.com.digitalhouse.model.Doador;
import br.com.digitalhouse.service.DoadorService;

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

}
