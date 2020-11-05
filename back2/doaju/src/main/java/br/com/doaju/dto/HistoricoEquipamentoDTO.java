package br.com.doaju.dto;

import java.util.Date;

import br.com.doaju.model.Equipamento;
import br.com.doaju.model.SituacaoEquipamento;
import br.com.doaju.model.Usuario;
import lombok.Data;

@Data
public class HistoricoEquipamentoDTO {
	
	private Long id;
	private Equipamento equipamento;
	private SituacaoEquipamento situacao;
	private Date dataAlteracao;
	private Usuario usuario;
}
