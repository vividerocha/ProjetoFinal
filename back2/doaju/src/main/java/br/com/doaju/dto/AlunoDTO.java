package br.com.doaju.dto;


import java.util.List;

import br.com.doaju.model.TipoEquipamento;

import br.com.doaju.model.Usuario;

import lombok.Data;

@Data
public class AlunoDTO {
	
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
	private List<TipoEquipamento> equipamentoAluno;
	private Usuario usuario;


}
