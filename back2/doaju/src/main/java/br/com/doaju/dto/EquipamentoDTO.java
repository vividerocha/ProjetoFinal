package br.com.doaju.dto;

import java.util.Date;

import br.com.doaju.model.TipoEquipamento;
import lombok.Data;

@Data
public class EquipamentoDTO {
	
	private Long id;
	String descricaoEquipamento;
	boolean funcionando;
	TipoEquipamento tipo;
	Date dataCadastro;
}
