package br.com.doaju.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class SituacaoEquipamento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String situacao;
	
	@OneToMany(mappedBy = "situacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HistoricoEquipamento> historicoEquipamentos = new ArrayList<>();

}
