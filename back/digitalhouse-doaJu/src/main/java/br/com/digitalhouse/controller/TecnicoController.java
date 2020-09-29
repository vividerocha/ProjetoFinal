package br.com.digitalhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalhouse.dto.TecnicoDTO;
import br.com.digitalhouse.model.Tecnico;
import br.com.digitalhouse.service.TecnicoService;

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

}
