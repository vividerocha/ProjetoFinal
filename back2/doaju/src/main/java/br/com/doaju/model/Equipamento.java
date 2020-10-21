package br.com.doaju.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
    @JoinColumn(name="tipoEquipamento_id", nullable=false)
	private TipoEquipamento tipoEquipamento = new TipoEquipamento();
	
	@OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HistoricoEquipamento> historicos = new ArrayList<>();

	
	
	

	
}
