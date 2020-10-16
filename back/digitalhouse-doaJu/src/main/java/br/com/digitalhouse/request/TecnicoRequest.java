package br.com.digitalhouse.request;

import br.com.digitalhouse.model.Pessoa;
import lombok.Data;

@Data
public class TecnicoRequest {
	private Long id;
    private Pessoa pessoa;
}
