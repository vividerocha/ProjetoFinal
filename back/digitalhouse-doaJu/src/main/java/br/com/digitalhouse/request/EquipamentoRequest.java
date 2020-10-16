package br.com.digitalhouse.request;

import javax.validation.constraints.NotBlank;

import br.com.digitalhouse.model.TipoEquipamento;
import lombok.Data;

@Data
public class EquipamentoRequest {
	
	private Long id;
	@NotBlank
	String descricao;
	@NotBlank
	boolean funcionando;
	@NotBlank
	TipoEquipamento tipo;
}
