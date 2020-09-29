package br.com.digitalhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalhouse.dto.AlunoDTO;
import br.com.digitalhouse.model.Aluno;
import br.com.digitalhouse.service.AlunoService;

@RequestMapping("/alunos")
@RestController
public class AlunoController {

	private final AlunoService alunoService;

	@Autowired
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	@PostMapping
	public ResponseEntity<Aluno> salvar(@RequestBody AlunoDTO dto) {
	    Aluno aluno = alunoService.salvar(dto.transformaParaObjeto());
	    return new ResponseEntity<>(aluno, HttpStatus.CREATED);
	}

}
