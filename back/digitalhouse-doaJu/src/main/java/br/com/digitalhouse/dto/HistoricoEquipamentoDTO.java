package br.com.digitalhouse.dto;

import java.time.LocalDate;

import br.com.digitalhouse.model.Equipamento;
import br.com.digitalhouse.model.SituacaoEquipamento;
import lombok.Data;

@Data
public class HistoricoEquipamentoDTO {
	
	private Long id;
	Equipamento equipamento;
	SituacaoEquipamento situacao;
	private LocalDate dataAlteracao;
}
