package br.com.doaju.request;

import java.time.LocalDate;

import br.com.doaju.model.Equipamento;
import br.com.doaju.model.SituacaoEquipamento;
import lombok.Data;

@Data
public class HistoricoEquipamentoRequest {
	
	private Long id;
	Equipamento equipamento;
	SituacaoEquipamento situacao;
	private LocalDate dataAlteracao;
	private Long idUsuario;
}
