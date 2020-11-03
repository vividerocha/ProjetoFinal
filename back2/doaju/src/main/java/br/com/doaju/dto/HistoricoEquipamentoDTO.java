package br.com.doaju.dto;

import java.time.LocalDate;

import br.com.doaju.model.Equipamento;
import br.com.doaju.model.SituacaoEquipamento;
import br.com.doaju.model.Usuario;
import lombok.Data;

@Data
public class HistoricoEquipamentoDTO {
	
	private Long id;
	private Equipamento equipamento;
	private SituacaoEquipamento situacao;
	private LocalDate dataAlteracao;
	private Usuario usuario;
}
