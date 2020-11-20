package br.com.doaju.dto.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.SituacaoEquipamentoDTO;
import br.com.doaju.request.SituacaoEquipamentoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Controller de Situação do Equipamento")

public interface SituacaoEquipamentoControllerSwagger {
	
	@ApiOperation("Lista a situação de um equipamento")
	public List<SituacaoEquipamentoDTO> listar();

	@ApiOperation("Salva a situação de um equipamento")
	public ResponseEntity<?> salvar(@RequestBody @Valid SituacaoEquipamentoRequest situacaoEquipamentoRequest);
	
	@ApiOperation("Busca a situação de um equipamento")
	public ResponseEntity<?> buscarEmail1(@PathVariable String desc);
	
	@ApiOperation("Atualiza a situação de um equipamento")
	public ResponseEntity<?> atualizar(@RequestBody SituacaoEquipamentoRequest situacaoRequest, @PathVariable Long id);
	
	@ApiOperation("Exclui a situação de um equipamento")
	public void excluir(@PathVariable Long id);
	
	@ApiOperation("Buscar a situação de um equipamento por e-mail")
	public ResponseEntity<?> buscarEmail(@PathVariable String desc);
}
