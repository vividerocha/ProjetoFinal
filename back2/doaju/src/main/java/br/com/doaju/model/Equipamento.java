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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import lombok.Data;

@Data
@Entity
public class Equipamento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	String descricao;
	boolean funcionando;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="tipoEquipamento_id",referencedColumnName="id",nullable=false,unique=true)
	TipoEquipamento tipo;
	
	@OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HistoricoEquipamento> historicos = new ArrayList<>();
	
	
	public Equipamento() {}

	public Equipamento(Long id, String descricao, boolean funcionando, TipoEquipamento tipo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.funcionando = funcionando;
		this.tipo = tipo;
	}

	
}
