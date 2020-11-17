package br.com.doaju.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import br.com.doaju.model.HistoricoEquipamento;
import br.com.doaju.model.TipoEquipamento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipamentoRequest {
	
	private Long id;
	@NotBlank
	private String descricaoEquipamento;
	private boolean funcionando;
	TipoEquipamento tipoEquipamento;
}
