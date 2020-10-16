package br.com.digitalhouse.dto;

import br.com.digitalhouse.model.Pessoa;
import lombok.Data;

@Data
public class DoadorDTO {
	
	private Long id;
    private Pessoa pessoa;

}
