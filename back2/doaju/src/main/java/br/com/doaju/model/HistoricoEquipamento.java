package br.com.doaju.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class HistoricoEquipamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
	private Equipamento equipamento;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "situacao_equipamento_id", nullable = false)
	private SituacaoEquipamento situacao;
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataAlteracao = new java.sql.Date(System.currentTimeMillis());
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

}
