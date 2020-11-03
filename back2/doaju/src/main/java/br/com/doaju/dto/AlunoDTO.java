package br.com.doaju.dto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.doaju.model.TipoEquipamento;
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
	//private List<String> equipamentos = new ArrayList<>();
	private Set<TipoEquipamento> equipamentoAluno = new HashSet<>();

}
