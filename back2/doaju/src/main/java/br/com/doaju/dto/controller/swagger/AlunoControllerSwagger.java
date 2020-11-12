package br.com.doaju.dto.controller.swagger;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.request.AlunoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Alunos")
public interface AlunoControllerSwagger {

	
	@ApiOperation("Cadastra um aluno")
	@ApiResponses({
		@ApiResponse(code=201, message = "Aluno cadastrado com sucesso")
		//podemos customizar mais códigos http aqui
	})
	ResponseEntity<?> salvar(@RequestBody AlunoRequest alunoRequest);
	
}
