package br.com.digitalhouse.dto;

import br.com.digitalhouse.model.Pessoa;
import lombok.Data;

@Data
public class AlunoDTO {
	
	private Long id;
	private String escola;
	private String turma;
	private String turno;
	private String serie;
    private Pessoa pessoa;

}
