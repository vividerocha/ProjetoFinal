package br.com.doaju.dto;

import br.com.doaju.model.TipoEquipamento;
import lombok.Data;

@Data
public class EquipamentoDTO {
	
	private Long id;
	String descricao;
	boolean funcionando;
	TipoEquipamento tipo;
}
