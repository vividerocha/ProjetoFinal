package br.com.doaju.request;

import javax.validation.constraints.NotBlank;

import br.com.doaju.model.TipoEquipamento;
import lombok.Data;

@Data
public class EquipamentoRequest {
	
	private Long id;
	@NotBlank
	String descricao;
	
	boolean funcionando;
	
	TipoEquipamento tipo;
}
