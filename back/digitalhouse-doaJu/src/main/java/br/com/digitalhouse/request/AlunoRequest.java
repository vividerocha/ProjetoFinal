package br.com.digitalhouse.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class AlunoRequest {	
	
	private Long id;
	@NotNull
	private int tipoPessoa;
	@NotNull
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
	
}
