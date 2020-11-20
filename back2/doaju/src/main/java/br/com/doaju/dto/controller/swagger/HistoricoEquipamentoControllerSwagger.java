package br.com.doaju.dto.controller.swagger;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.EquipamentoDTO;
import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
import br.com.doaju.dto.HistoricoEquipamentoDTO;
import br.com.doaju.model.Equipamento;
import br.com.doaju.request.EquipamentoRequest;
import br.com.doaju.request.HistoricoEquipamentoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Histórico do Equipamento")

public interface HistoricoEquipamentoControllerSwagger {
	
	@ApiOperation("Lista o histórico de um equipamento")
	public List<HistoricoEquipamentoDTO> listar();
	
	@ApiOperation("Salva o histórico de um equipamento")
	public ResponseEntity<?> salvar(@RequestBody HistoricoEquipamentoRequest historicoEquipamentoRequest);
	
	@ApiOperation("Busca o histórico de um equipamento")
	public ResponseEntity<?> buscar(@PathVariable Equipamento equipamento);
	
	@ApiOperation("Busca o histórico de um equipamento por id")
	public List<HistoricoEquipamentoDTO> buscarHistoricoEquipamento(@PathVariable Long idEquipamento);
	
	@ApiOperation("Busca um equipamento para um técnico")
	public List<EquipamentosTecnicoRegiaoDTO> buscarEquipamentoParaTecnico(@PathVariable Long id);
	
	@ApiOperation("Busca o último histórico de um equipamento")
	public Long buscarUltimoHistoricoEquipamento(@PathVariable Long id);

}
