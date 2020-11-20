package br.com.doaju.dto.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.TecnicoDTO;
import br.com.doaju.request.TecnicoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Controller de Técnico")


public interface TecnicoControllerSwagger {
	
	@ApiOperation("Lista um técnico")
	public List<TecnicoDTO> listar();
	
	@ApiOperation("Salva um técnico")
	public ResponseEntity<?> salvar(@RequestBody TecnicoRequest tecnicoRequest);
	
	@ApiOperation("Busca um técnico")
	public ResponseEntity<?> buscar(@PathVariable Long id);
	
	@ApiOperation("Atualiza um técnico")
	public ResponseEntity<?> atualizar(@RequestBody TecnicoRequest tecnicoRequest, @PathVariable Long id);
	
	@ApiOperation("Exclui um técnico")
	public void excluir(@PathVariable Long id);
	
	@ApiOperation("Busca um técnico por id")
	public ResponseEntity<?> buscarPorIdUser(@PathVariable Long id);
}
