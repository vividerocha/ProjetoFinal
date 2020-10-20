package br.com.doaju.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.doaju.model.HistoricoEquipamento;
import lombok.Data;

@Data
public class SituacaoEquipamentoDTO {

	private Long id;
	private String situacao;
	private List<HistoricoEquipamento> historicoEquipamentos = new ArrayList<>();
}
