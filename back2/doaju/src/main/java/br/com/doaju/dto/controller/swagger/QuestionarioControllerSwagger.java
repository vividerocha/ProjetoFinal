package br.com.doaju.dto.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.QuestionarioDTO;
import br.com.doaju.dto.RankingDTO;
import br.com.doaju.model.Questionario;
import br.com.doaju.request.QuestionarioRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Controller de Questionário")
public interface QuestionarioControllerSwagger {
	
	@ApiOperation("Lista um questionário")
	public List<QuestionarioDTO> listar();
	
	@ApiOperation("Salva um questionário")
	public ResponseEntity<?> salvar(@RequestBody QuestionarioRequest questionarioRequest);
	
	@ApiOperation("Busca um questionário")
	public ResponseEntity<?> buscar(@PathVariable Long id);
	
	@ApiOperation("Atualiza um questionário")
	public ResponseEntity<?> atualizar(@RequestBody @Valid QuestionarioRequest questionarioRequest, @PathVariable Long id);
	
	@ApiOperation("Exclui um questionário")
	public ResponseEntity<Questionario> excluir(@PathVariable Long id);
	
	@ApiOperation("Busca um questionário por id")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id);
	
	@ApiOperation("Lista o ranking de um questionário por região")
	public List<RankingDTO> listarRanking(@PathVariable String regiao);
	
	
	
}
