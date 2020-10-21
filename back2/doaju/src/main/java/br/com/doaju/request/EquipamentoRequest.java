package br.com.doaju.request;

import javax.validation.constraints.NotBlank;

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
