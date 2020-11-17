package br.com.doaju.request;


import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoEquipamentoRequest {
	
	private Long id;
	@NotBlank
	private String descricao;	
}
