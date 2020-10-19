package br.com.doaju.dto;

import java.time.LocalDate;

import br.com.doaju.model.Equipamento;
import br.com.doaju.model.SituacaoEquipamento;
import lombok.Data;

@Data
public class HistoricoEquipamentoDTO {
	
	private Long id;
	Equipamento equipamento;
	SituacaoEquipamento situacao;
	private LocalDate dataAlteracao;
}
