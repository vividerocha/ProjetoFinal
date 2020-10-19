package br.com.doaju.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Tecnico implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());

}
