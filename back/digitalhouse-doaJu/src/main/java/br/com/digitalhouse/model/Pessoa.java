package br.com.digitalhouse.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // JPA Only
public class Pessoa {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "idPessoa")
		private Long id;

		@Column
		private int tipoPessoa;
		@Column
		private String nomeCompleto;
		@Column
		private String cep;
		@Column
		private String logradouro;
		@Column
		private int numeroCasa;
		@Column
		private String bairro;
		@Column
		private String cidade;
		@Column
		private String estado;
		@Column
		private String complemento;
		@Column
		private String telefoneFixo;
		@Column
		private String telefoneCelular;
		@Column
		private boolean termo;
		
		@DateTimeFormat(pattern="yyyy-mm-dd")
		@Column(name="data_cadastro")
		private LocalDate dataCadastro;
		
		@Column
		private int id_usuario;
		
		public Pessoa(int tipoPessoa, String nomeCompleto, String cep,
						String logradouro, int numeroCasa, String bairro,
						String cidade, String estado, String complemento, String telefoneFixo,
						String telefoneCelular, boolean termo, int id_usuario) {
			//1 - Doador
			//2 - Tecnico
			//3 - Aluno
	        this.tipoPessoa = tipoPessoa;
	        this.nomeCompleto = nomeCompleto;
	        this.cep = cep;
	        this.logradouro = logradouro;
	        this.numeroCasa = numeroCasa;
	        this.bairro = bairro;
	        this.cidade = cidade;
	        this.estado = estado;
	        this.complemento = complemento;
	        this.telefoneFixo = telefoneFixo;
	        this.telefoneCelular = telefoneCelular;
	        this.termo = termo;
	        this.id_usuario = id_usuario;
	    }
}
