package br.com.doaju.request;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AlunoRequest{

	private Long id;
	private String escola;
	private String turma;
	private String turno;
	private String serie;
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
	private List<String> equipamentos = new ArrayList<>();
	
}
