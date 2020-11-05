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
import br.com.doaju.dto.EquipamentoDTO;
import br.com.doaju.request.EquipamentoRequest;
import br.com.doaju.service.EquipamentoService;

@CrossOrigin
@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
	@Autowired
	private EquipamentoService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody EquipamentoRequest equipamentoRequest) {	
		try {
			equipamentoRequest.setId(null);
			EquipamentoDTO equipamentoDTO = service.salvar(equipamentoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	
	@GetMapping
	public List<EquipamentoDTO> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		EquipamentoDTO equipamento = service.buscar(id);
		
		if (equipamento != null) {
			return ResponseEntity.ok(equipamento);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Equipamento!");
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody EquipamentoRequest equipamentoRequest, @PathVariable Long id) {
		
		EquipamentoDTO equipamentoAtual = service.buscar(id);
		
		if (equipamentoAtual != null) {
			BeanUtils.copyProperties(equipamentoRequest, equipamentoAtual, "id");
			
			service.atualizar(equipamentoRequest);
			return ResponseEntity.ok(equipamentoRequest);
		}	
			
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@GetMapping("/doador/{idDoador}")
	public List<EquipamentoDTO> buscarPorUsuario(@PathVariable Long idDoador) {
		
		//Faz a consulta dos equipamentos relacionados ao doador logado
		return service.buscarPorDoador(idDoador);
			
	}

}
