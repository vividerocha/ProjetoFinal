package br.com.digitalhouse.request;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TipoEquipamentoRequest {
	private Long id;
	@NotBlank
	private String descricao;	
}
