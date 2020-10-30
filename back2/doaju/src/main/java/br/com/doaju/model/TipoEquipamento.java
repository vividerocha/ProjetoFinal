package br.com.doaju.model;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TipoEquipamento", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class TipoEquipamento{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String descricao;
		
	@OneToMany(cascade=CascadeType.ALL, mappedBy="tipoEquipamento", fetch=FetchType.EAGER)
	@JsonManagedReference
	@Transient 
	private Set<Equipamento> equipamentos;
		
}
