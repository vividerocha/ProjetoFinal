package br.com.doaju.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class EquipamentosTecnicoRegiao {

	private Long idEquipamento;
	private String tipoEquipamento;
	private String descricaoEquipamento;
	private boolean funcionando;
	private String situacao;
	private String nomeDoador;
	private String telefoneDoador;
	private String celularDoador;
	private String enderecoDoador;
	private String estadoDoador;
	private String cidade;
	private int casaDoador;
	
	
	public EquipamentosTecnicoRegiao(Long idEquipamento, String tipoEquipamento, String descricaoEquipamento, boolean funcionando,
			String situacao, String nomeDoador, String telefoneDoador, String celularDoador, String enderecoDoador,
			String cidade, String estadoDoador, int casaDoador) {
		super();
		
		this.idEquipamento = idEquipamento;
		this.tipoEquipamento = tipoEquipamento;
		this.descricaoEquipamento = descricaoEquipamento;
		this.funcionando = funcionando;
		this.situacao = situacao;
		this.nomeDoador = nomeDoador;
		this.telefoneDoador = telefoneDoador;
		this.celularDoador = celularDoador;
		this.enderecoDoador = enderecoDoador;
		this.cidade = cidade;
		this.estadoDoador = estadoDoador;
		this.casaDoador = casaDoador;
	}
	
	
}
