package br.com.digitalhouse.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor // JPA Only
@Entity
public class Tecnico extends Pessoa{

	public Tecnico(int tipoPessoa, String nomeCompleto, String cep,
			String logradouro, int numeroCasa, String bairro,
			String cidade, String uf, String complemento, String telefone,
			String celular, boolean termo, int idUsuario) {

		//tipo pessoa 2 - Tecnico
		super(2,nomeCompleto, cep, logradouro, numeroCasa, bairro,
				cidade, uf, complemento, telefone, celular, termo, idUsuario);
	}
}
