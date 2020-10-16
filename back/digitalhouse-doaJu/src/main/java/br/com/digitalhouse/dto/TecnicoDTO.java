package br.com.digitalhouse.dto;

import br.com.digitalhouse.model.Pessoa;
import lombok.Data;

@Data
public class TecnicoDTO {
	private Long id;
    private Pessoa pessoa;
}
