package br.com.doaju.dto;

import java.util.Set;

import br.com.doaju.model.Equipamento;
import lombok.Data;

@Data
public class TipoEquipamentoDTO {
	
	private Long id;
	private String descricao;
	private Set<Equipamento> equipamentos;
	
}
