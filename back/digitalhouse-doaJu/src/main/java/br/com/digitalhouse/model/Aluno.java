package br.com.digitalhouse.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor // JPA Only
@Entity
public class Aluno extends Pessoa{

	public Aluno(int tipoPessoa, String nomeCompleto, String cep,
			String logradouro, int numeroCasa, String bairro,
			String cidade, String uf, String complemento, String telefoneFixo,
			String telefoneCelular, boolean termo, int idUsuario) {

		//tipo pessoa 3 - Aluno
		super(3,nomeCompleto, cep, logradouro, numeroCasa, bairro,
				cidade, uf, complemento, telefoneFixo, telefoneCelular, termo, idUsuario);
	}
}
