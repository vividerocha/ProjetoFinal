package br.com.doaju.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "HistoricoEquipamento")
public class HistoricoEquipamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="equipamento_id",referencedColumnName="id",nullable=false,unique=true)
	private Equipamento equipamento;
	
	@ManyToOne
    @JoinColumn(name="situacaoEquipamento_id", nullable=false)
    @JsonBackReference
	private SituacaoEquipamento situacao;
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataAlteracao = new java.sql.Date(System.currentTimeMillis());
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

}
