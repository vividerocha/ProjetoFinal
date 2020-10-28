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
import br.com.doaju.dto.SituacaoEquipamentoDTO;
import br.com.doaju.model.SituacaoEquipamento;
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
	
	@GetMapping("/desc/{desc}")
	public ResponseEntity<?> buscarEmail(@PathVariable String desc) {

		SituacaoEquipamento situacao = service.buscarTipo(desc);
		
		if (desc != "") {
			if (situacao != null) {
				return ResponseEntity.ok(situacao);
			} else {
				// return ResponseEntity.notFound().build();
				return ResponseEntity.badRequest().body("Não existe um tipo com essa descrição!");
			}

		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		SituacaoEquipamentoDTO tipo = service.buscar(id);
		
		if (tipo != null) {
			return ResponseEntity.ok(tipo);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody SituacaoEquipamentoRequest situacaoRequest, @PathVariable Long id) {
		
		SituacaoEquipamentoDTO situacaoAtual = service.buscar(id);
		
		if (situacaoAtual != null) {
			BeanUtils.copyProperties(situacaoRequest, situacaoAtual, "id");
			
			service.atualizar(situacaoRequest);
			return ResponseEntity.ok(situacaoRequest);
		}	
			
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}

}
