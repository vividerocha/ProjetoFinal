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

import br.com.doaju.dto.TipoEquipamentoDTO;
import br.com.doaju.model.TipoEquipamento;
import br.com.doaju.request.TipoEquipamentoRequest;
import br.com.doaju.service.TipoEquipamentoService;

@CrossOrigin
@RestController
@RequestMapping("/tipoEquipamentos")
public class TipoEquipamentoController {
	@Autowired
	private TipoEquipamentoService service;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid TipoEquipamentoRequest tipoEquipamentoRequest) {	
		try {
			
			TipoEquipamentoDTO tipoEquipamentoDTO = service.salvar(tipoEquipamentoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(tipoEquipamentoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<TipoEquipamentoDTO> listar(){
		return service.listar();
	}
	
	@GetMapping("/desc/{desc}")
	public ResponseEntity<?> buscarEmail(@PathVariable String desc) {

		TipoEquipamento tipo = service.buscarTipo(desc);
		if (desc != "") {
			if (tipo != null) {
				return ResponseEntity.ok(tipo);
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
		
		TipoEquipamentoDTO tipo = service.buscar(id);
		
		if (tipo != null) {
			return ResponseEntity.ok(tipo);
		}
		
		return ResponseEntity.badRequest().body("Não retornou Usuário!");
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody TipoEquipamentoRequest tipoRequest, @PathVariable Long id) {
		
		TipoEquipamentoDTO tipoAtual = service.buscar(id);
		
		if (tipoAtual != null) {
			BeanUtils.copyProperties(tipoRequest, tipoAtual, "id");
			
			service.atualizar(tipoRequest);
			return ResponseEntity.ok(tipoRequest);
		}	
			
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
}
