package br.com.doaju.dto.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.DoadorDTO;
import br.com.doaju.request.DoadorRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Doadores")
public interface DoadorControllerSwagger {
	@ApiOperation("Cadastra um doador")
	@ApiResponses({
		@ApiResponse(code=201, message = "Doador cadastrado com sucesso")
		//podemos customizar mais códigos http aqui
	})
	public ResponseEntity<?> salvar(@RequestBody DoadorRequest doadorRequest);
	
	@ApiOperation("Lista um doador")
	public List<DoadorDTO> listar();
	
	@ApiOperation("Busca um doador por id")
	public ResponseEntity<?> buscar(@PathVariable Long id);
	
	@ApiOperation("Atualiza um doador por id")
	public ResponseEntity<?> atualizar(@RequestBody DoadorRequest doadorRequest, @PathVariable Long id);
	
	@ApiOperation("Deleta um doador por id")
	public void excluir(@PathVariable Long id);
	
	@ApiOperation("Busca um doador por id do doador")
	public ResponseEntity<?> buscarPorIdUser(@PathVariable Long id);
	
}
