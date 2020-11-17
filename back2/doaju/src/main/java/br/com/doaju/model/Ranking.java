package br.com.doaju.model;

import lombok.Data;

@Data
public class Ranking {
	
	private Long idAluno;
	private String nomeAluno;
	private String regiaoAluno;
	private Long pontuacaoAluno;
	
	public Ranking(Long idAluno, String nomeAluno, String regiaoAluno, Long pontuacaoAluno) {
		super();
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		this.regiaoAluno = regiaoAluno;
		this.pontuacaoAluno = pontuacaoAluno;
	}

}
