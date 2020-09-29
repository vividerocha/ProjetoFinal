package br.com.digitalhouse.dto;

import br.com.digitalhouse.model.Aluno;
import lombok.Getter;

@Getter
public class AlunoDTO {

	private int tipoPessoa;
	private String nomeCompleto;
	private String cep;
	private String logradouro;
	private int numeroCasa;
	private String bairro;
	private String cidade;
	private String uf;
	private String complemento;
	private String telefoneFixo;
	private String telefoneCelular;
	private boolean termo;

	public Aluno transformaParaObjeto() {
		return new Aluno(tipoPessoa, nomeCompleto, cep, logradouro, numeroCasa, bairro,
				cidade, uf, complemento, telefoneFixo, telefoneCelular, termo);
	}
}