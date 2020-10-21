package br.com.doaju.dto;

import br.com.doaju.model.TipoEquipamento;
import lombok.Data;

@Data
public class EquipamentoDTO {
	
	private Long id;
	private String descricaoEquipamento;
	boolean funcionando;
	TipoEquipamento tipoEquipamento;
	
	
}
