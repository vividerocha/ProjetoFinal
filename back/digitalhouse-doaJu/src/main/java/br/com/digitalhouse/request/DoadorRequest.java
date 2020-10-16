package br.com.digitalhouse.request;

import br.com.digitalhouse.model.Pessoa;
import lombok.Data;

@Data
public class DoadorRequest {
	
	private Long id;
    private Pessoa pessoa;
}
