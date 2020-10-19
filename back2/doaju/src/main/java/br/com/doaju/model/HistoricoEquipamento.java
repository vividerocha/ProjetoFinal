package br.com.doaju.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private LocalDate dataAlteracao;
	
	

}
