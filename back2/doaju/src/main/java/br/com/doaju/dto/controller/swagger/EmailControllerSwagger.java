package br.com.doaju.dto.controller.swagger;

import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import br.com.doaju.dto.MensagemDTO;


import br.com.doaju.request.MensagemRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Controller de Email")
public interface EmailControllerSwagger {
	
	
	@ApiOperation("Lista um email")
	public List<MensagemDTO> listar();
	
	@ApiOperation("Envia um email")
	public void sendmail(@RequestBody MensagemRequest mensagemRequest);
	
	@ApiOperation("Busca um email")
	public ResponseEntity<?> buscar(@PathVariable Long id);
	
	@ApiOperation("Envia senha de um email")
	public void enviarEmailSenha(@RequestBody MensagemRequest mensagemRequest);

}
