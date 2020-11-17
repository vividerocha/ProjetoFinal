package br.com.doaju.dto.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.AlunoDTO;
import br.com.doaju.model.Aluno;
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
	
	@ApiOperation("Lista todos os alunos")
	public List<AlunoDTO> listar();
	
	@ApiOperation("Busca aluno por id do usuário")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id);
	
	@ApiOperation("Deleta aluno por id")
	public ResponseEntity<Aluno> excluir(@PathVariable Long id);
	
	@ApiOperation("Busca aluno por id")
	public ResponseEntity<?> buscar(@PathVariable Long id);
	
	@ApiOperation("Atualiza aluno por id")
	public ResponseEntity<?> atualizar(@RequestBody @Valid AlunoRequest alunoRequest, @PathVariable Long id);
	
}

