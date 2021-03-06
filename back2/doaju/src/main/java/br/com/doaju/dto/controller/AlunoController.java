package br.com.doaju.dto.controller;

import java.util.List;

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
import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
import br.com.doaju.dto.controller.swagger.AlunoControllerSwagger;
import br.com.doaju.model.Aluno;
import br.com.doaju.request.AlunoRequest;
import br.com.doaju.service.AlunoService;
import br.com.doaju.service.HistoricoEquipamentoService;
  
@CrossOrigin
@RestController
@RequestMapping("/alunos")


public class AlunoController implements AlunoControllerSwagger{
	
	@Autowired
	private AlunoService service;
	
	@Autowired
	private HistoricoEquipamentoService serviceH;
	
	@PostMapping
	@Override
	public ResponseEntity<?> salvar(@RequestBody AlunoRequest alunoRequest) {
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
		
		AlunoDTO aluno = service.buscar(id);
		
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		
		AlunoDTO aluno = service.buscarPorIdUsuario(id);
		
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/equipamento/{id}")
	public EquipamentosTecnicoRegiaoDTO buscarEquipamento(@PathVariable Long id) {
		return serviceH.buscaEquipamentoDistribuido(id);
	}
	
	@GetMapping("/tecnico/{id}")
	public EquipamentosTecnicoRegiaoDTO buscarTecnico(@PathVariable Long id) {
		return serviceH.buscaTecnico(id);
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
	@Override
	public ResponseEntity<?> atualizar(@RequestBody AlunoRequest alunoRequest, @PathVariable Long id) {
		


		Aluno alunoAtual = service.buscar2(id).orElse(null);


		if (alunoAtual != null) {
			BeanUtils.copyProperties(alunoRequest, alunoAtual, "id");			

			service.atualizar2(alunoAtual);

			return ResponseEntity.ok(alunoAtual);
		}	
			
		return ResponseEntity.notFound().build();
	}

}
