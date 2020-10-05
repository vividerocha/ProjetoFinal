package br.com.digitalhouse.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public List<Aluno> listar(){
		return alunoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
		
		Optional<Aluno> aluno = alunoService.buscar(id);
		
		if (aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> excluir(@PathVariable Long id) {
		try {
			alunoService.excluir(id);	
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
			
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody @Valid Aluno aluno, @PathVariable Long id) {
		
		Aluno alunoAtual = alunoService.buscar(id).orElse(null);
		
		if (alunoAtual != null) {
			BeanUtils.copyProperties(aluno, alunoAtual, "id");
			
			alunoService.atualizar(alunoAtual);
			return ResponseEntity.ok(alunoAtual);
		}	
			
		return ResponseEntity.notFound().build();
	}

}
