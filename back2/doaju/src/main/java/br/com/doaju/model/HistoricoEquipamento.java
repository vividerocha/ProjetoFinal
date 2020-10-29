package br.com.doaju.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Data;

@Data
@Entity
public class HistoricoEquipamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="equipamento_id",referencedColumnName="id",nullable=false,unique=true)
	Equipamento equipamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="situacaoEquipamento_id",referencedColumnName="id",nullable=false,unique=true)
	SituacaoEquipamento situacao;
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataAlteracao = new java.sql.Date(System.currentTimeMillis());
	
	

}
