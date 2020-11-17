package br.com.doaju.dto.controller;

import java.util.List;

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

import br.com.doaju.dto.AlunoDTO;
import br.com.doaju.dto.QuestionarioDTO;
import br.com.doaju.dto.RankingDTO;
import br.com.doaju.mapper.AlunoMapper;
import br.com.doaju.model.Aluno;
import br.com.doaju.model.Questionario;
import br.com.doaju.request.QuestionarioRequest;
import br.com.doaju.service.AlunoService;
import br.com.doaju.service.QuestionarioService;

@CrossOrigin
@RestController
@RequestMapping("/questionario")
public class QuestionarioController {
	
	@Autowired
	private QuestionarioService service;
	
	@Autowired
	private AlunoService serviceAl;
	
	@Autowired
	private AlunoMapper mapperAl;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody QuestionarioRequest questionarioRequest) {	
		try {
			
			QuestionarioDTO questionarioDTO = service.salvar(questionarioRequest);
			Aluno aluno = serviceAl.buscar2(questionarioRequest.getAluno()).get();
			questionarioDTO.setAluno(aluno);
			return ResponseEntity.status(HttpStatus.CREATED).body(questionarioDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<QuestionarioDTO> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		QuestionarioDTO questionario = service.buscar(id);
		
		if (questionario != null) {
			return ResponseEntity.ok(questionario);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Questionário!");
	
	}
	@GetMapping("/aluno/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		
		QuestionarioDTO questionario = service.buscarPorIdAluno(id);
		
		if (questionario != null) {
			return ResponseEntity.ok(questionario);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/ranking/{regiao}")
	public List<RankingDTO> listarRanking(@PathVariable String regiao) {
		
		return service.buscarRankingRegiao(regiao);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Questionario> excluir(@PathVariable Long id) {
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
	public ResponseEntity<?> atualizar(@RequestBody @Valid QuestionarioRequest questionarioRequest, @PathVariable Long id) {
		

		QuestionarioDTO questionarioAtual = service.buscar(id);

		if (questionarioAtual != null) {
			BeanUtils.copyProperties(questionarioRequest, questionarioAtual, "id");
			
			service.atualizar(questionarioRequest);
			return ResponseEntity.ok(questionarioAtual);
		}	
			
		return ResponseEntity.notFound().build();
	}
}