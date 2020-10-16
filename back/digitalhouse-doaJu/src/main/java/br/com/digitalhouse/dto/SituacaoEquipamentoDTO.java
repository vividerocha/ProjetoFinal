package br.com.digitalhouse.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.model.HistoricoEquipamento;
import lombok.Data;

@Data
public class SituacaoEquipamentoDTO {

	private Long id;
	private String descricao;
	private List<HistoricoEquipamento> historicoEquipamentos = new ArrayList<>();
}
