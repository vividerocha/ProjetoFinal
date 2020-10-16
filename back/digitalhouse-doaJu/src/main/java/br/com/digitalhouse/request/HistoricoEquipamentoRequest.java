package br.com.digitalhouse.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.digitalhouse.model.Equipamento;
import br.com.digitalhouse.model.SituacaoEquipamento;
import lombok.Data;

@Data
public class HistoricoEquipamentoRequest {
	
	private Long id;
	@NotBlank
	Equipamento equipamento;
	@NotBlank
	SituacaoEquipamento situacao;
	@NotBlank
	private LocalDate dataAlteracao;

}
