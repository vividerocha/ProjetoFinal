package br.com.doaju.dto;

import lombok.Data;

@Data
public class EquipamentoDTO {
	
	private Long id;
	private String descricaoEquipamento;
	boolean funcionando;
	TipoEquipamentoDTO tipoEquipamento;
	
	
}
