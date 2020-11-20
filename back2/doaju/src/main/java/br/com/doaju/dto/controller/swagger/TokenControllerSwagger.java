package br.com.doaju.dto.controller.swagger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.doaju.dto.EquipamentoDTO;
import br.com.doaju.request.EquipamentoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Controller de Token")

public interface TokenControllerSwagger {
	
	@ApiOperation("Exclui um token")
	public void revoke(HttpServletRequest req, HttpServletResponse resp);

}
