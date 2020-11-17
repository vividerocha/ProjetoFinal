package br.com.doaju.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Equipamento{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descricaoEquipamento;
	private boolean funcionando;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "tipoEquipamento_id", nullable = false)
	private TipoEquipamento tipoEquipamento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL)
	private List<HistoricoEquipamento> historico;
	
}
