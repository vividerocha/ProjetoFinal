package br.com.doaju.dto.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doaju.dto.DoadorDTO;
import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
//import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
import br.com.doaju.dto.HistoricoEquipamentoDTO;
import br.com.doaju.dto.TecnicoDTO;
import br.com.doaju.model.Equipamento;
import br.com.doaju.model.HistoricoEquipamento;
import br.com.doaju.request.HistoricoEquipamentoRequest;
import br.com.doaju.service.DoadorService;
import br.com.doaju.service.HistoricoEquipamentoService;
import br.com.doaju.service.TecnicoService;

@CrossOrigin
@RestController
@RequestMapping("/historicoEquipamento")
public class HistoricoEquipamentoController {
	@Autowired
	private HistoricoEquipamentoService service;
	
	@Autowired
	private TecnicoService serviceT;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody HistoricoEquipamentoRequest historicoEquipamentoRequest) {	
		try {
			
			HistoricoEquipamentoDTO historicoEquipamentoDTO = service.salvar(historicoEquipamentoRequest);			
			return ResponseEntity.status(HttpStatus.CREATED).body(historicoEquipamentoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}
	
	@GetMapping
	public List<HistoricoEquipamentoDTO> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Equipamento equipamento) {
		
		List<HistoricoEquipamento> historicoEquipamento = service.buscar(equipamento);
		
		if (!historicoEquipamento.isEmpty()) {
			return ResponseEntity.ok(historicoEquipamento);
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@GetMapping("/Equipamento/{idEquipamento}")
	public List<HistoricoEquipamentoDTO> buscarHistoricoEquipamento(@PathVariable Long idEquipamento) {
		return service.buscarHistorico(idEquipamento);
	
	}
	
	@GetMapping("/QuadroTecnico/{id}")
	public List<EquipamentosTecnicoRegiaoDTO> buscarEquipamentoParaTecnico(@PathVariable Long id) {
		TecnicoDTO tecnico = serviceT.buscarPoridUser(id);
		String regiao = tecnico.getEstado();
		return service.buscaEquipamentosParaReparoPorRegiao(regiao);
	
	}

}
