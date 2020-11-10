package br.com.doaju.dto;

import lombok.Data;

@Data
public class EquipamentosTecnicoRegiaoDTO {
	
	private Long idEquipamento;
	private String tipoEquipamento;
	private String descricaoEquipamento;
	private boolean funcionando;
	private String situacao;
	private String nomeDoador;
	private String telefoneDoador;
	private String celularDoador;
	private String enderecoDoador;
	private String cidade;
	private String estadoDoador;
	private int casaDoador;
}
