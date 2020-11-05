package br.com.doaju.dto;

import lombok.Data;

@Data
public class EquipamentosTecnicoRegiaoDTO {
	private String tipoEquipamento;
	private String descricaoEquipamento;
	private String funcionando;
	private String situacao;
	private String nomeDoador;
	private String telefoneDoador;
	private String celularDoador;
	private String enderecoDoador;
	private String estadoDoador;
	private String casaDoador;
}
