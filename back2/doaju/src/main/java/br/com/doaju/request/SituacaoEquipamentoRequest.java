package br.com.doaju.request;

import java.util.ArrayList;
import java.util.List;

import br.com.doaju.model.HistoricoEquipamento;
import lombok.Data;

@Data
public class SituacaoEquipamentoRequest {
	
	private Long id;
	private String descricao;
	private List<HistoricoEquipamento> historicoEquipamentos = new ArrayList<>();
	
	
}
