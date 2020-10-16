package br.com.digitalhouse.dto;


import lombok.Data;

@Data
public class TecnicoDTO {
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
}
