package br.com.doaju.dto.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.EquipamentoDTO;
import br.com.doaju.dto.TipoEquipamentoDTO;
import br.com.doaju.request.EquipamentoRequest;
import br.com.doaju.request.TipoEquipamentoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Controller de Tipo de Equipamento")



public interface TipoEquipamentoControllerSwagger {
	
	@ApiOperation("Lista o tipo de equipamento")
	public List<TipoEquipamentoDTO> listar();
	
	@ApiOperation("Salva o tipo de equipamento")
	public ResponseEntity<?> salvar(@RequestBody @Valid TipoEquipamentoRequest tipoEquipamentoRequest);
	
	@ApiOperation("Busca o tipo de equipamento")
	public ResponseEntity<?> buscar(@PathVariable Long id);
	
	@ApiOperation("Atualiza o tipo de equipamento")
	public ResponseEntity<?> atualizar(@RequestBody TipoEquipamentoRequest tipoRequest, @PathVariable Long id);
	
	@ApiOperation("Exclui o tipo de equipamento")
	public void excluir(@PathVariable Long id);
	
	@ApiOperation("Busca o tipo de equipamento por email")
	public ResponseEntity<?> buscarEmail(@PathVariable String desc);


}
