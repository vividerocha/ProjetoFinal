package br.com.doaju.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Aluno implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
	
	@Transient
	private List<String> equipamentos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "aluno_equipamento", joinColumns = @JoinColumn(name = "aluno_id"),
			inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
	private Set<TipoEquipamento> equipamentoAluno = new HashSet<>();
	
	public void addTipo(TipoEquipamento tipo) {
		this.equipamentoAluno.add(tipo);
	}

}
