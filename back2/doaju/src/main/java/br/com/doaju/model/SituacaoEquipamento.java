package br.com.doaju.model;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SituacaoEquipamento")
public class SituacaoEquipamento{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String situacao;
	
	@OneToMany(mappedBy="SituacaoEquipamento")
	@JsonManagedReference
	@Transient 
	private Set<HistoricoEquipamento> historico;

}
