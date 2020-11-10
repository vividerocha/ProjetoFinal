package br.com.doaju.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "equipamento")
public class Equipamento{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descricaoEquipamento;
	private boolean funcionando;
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
    @JoinColumn(name="tipoEquipamento_id", nullable=false)
    @JsonBackReference
	private TipoEquipamento tipoEquipamento;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="equipamento")
	@Fetch(value=FetchMode.SELECT)
	//@JsonManagedReference
	private Set<HistoricoEquipamento> historicos;
	
}
