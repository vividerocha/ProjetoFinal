package br.com.digitalhouse.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.com.digitalhouse.request.AlunoRequest;
import br.com.digitalhouse.service.AlunoService;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid AlunoRequest alunoRequest) {	
		try {
			
			AlunoDTO alunoDTO = service.salvar(alunoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(alunoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<AlunoDTO> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		Optional<Aluno> aluno = service.buscar(id);
		
		if (aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> excluir(@PathVariable Long id) {
		try {
			service.excluir(id);	
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
		
		Aluno alunoAtual = service.buscar(id).orElse(null);
		
		if (alunoAtual != null) {
			BeanUtils.copyProperties(aluno, alunoAtual, "id");
			
			service.atualizar(alunoAtual);
			return ResponseEntity.ok(alunoAtual);
		}	
			
		return ResponseEntity.notFound().build();
	}

}
