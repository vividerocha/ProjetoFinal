package br.com.digitalhouse.dto;

import br.com.digitalhouse.model.TipoEquipamento;
import lombok.Data;

@Data
public class EquipamentoDTO {
	
	private Long id;
	String descricao;
	boolean funcionando;
	TipoEquipamento tipo;
}
