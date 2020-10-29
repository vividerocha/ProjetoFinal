package br.com.doaju.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DoadorDTO {
	
	private Long id;
	private String nomeCompleto;	
	private String cep;
	private String logradouro;
	private int numeroCasa;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	private String telefone;
	private String celular;
	private boolean termo;
	private Date dataCadastro;
	

}
