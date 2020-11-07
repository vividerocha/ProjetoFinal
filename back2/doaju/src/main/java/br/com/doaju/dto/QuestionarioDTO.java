package br.com.doaju.dto;

import java.util.Date;
import br.com.doaju.model.Aluno;
import lombok.Data;

@Data
public class QuestionarioDTO {
	
	private Long id;
	private Long perg1;
	private Long perg2;
	private Long perg3;
	private Long perg4;
	private Long perg5;
	private Long perg6;
	private Long perg7;
	private Long perg8;
	private Long perg9;
	private Long perg10;
	private Long pontuacaoTotal;
	private Aluno aluno;
	     
	private Date dataAlteracao;

}
