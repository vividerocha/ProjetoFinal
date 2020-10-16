package br.com.digitalhouse.request;

import br.com.digitalhouse.model.Pessoa;
import lombok.Data;

@Data
public class AlunoRequest {
	
	private Long id;
	private String escola;
	private String turma;
	private String turno;
	private String serie;
	private Pessoa pessoa;
}
