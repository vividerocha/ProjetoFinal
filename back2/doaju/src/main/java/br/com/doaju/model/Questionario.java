package br.com.doaju.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Questionario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//Quantos carros tem na sua residência?
	private Long perg1;
	//Quantas motos tem na sua residência?
	private Long perg2;
	//Quantas televisões tem na sua residência?
	private Long perg3;
	//Quantas geladeiras tem na sua residência?
	private Long perg4;
	//Quantos banheiros tem na sua residência?
	private Long perg5;
	//Você mora de aluguel ou casa própria?
	private Long perg6;
	//Quantos computadores tem na sua residência?
	private Long perg7;
	//Quantos celulares ou tablets tem na sua residência?
	private Long perg8;
	//Quantas pessoas moram na sua residência?
	private Long perg9;
	//Tem internet fixa na sua residência?
	private Long perg10;
	
	private Long pontuacaoTotal;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "aluno_id")
	@JsonBackReference
	private Aluno aluno;
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataAlteracao = new java.sql.Date(System.currentTimeMillis());

}
