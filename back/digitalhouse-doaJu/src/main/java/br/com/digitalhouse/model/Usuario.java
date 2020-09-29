package br.com.digitalhouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // JPA Only
public class Usuario {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "idUsuario")
	    private Long id;
	    private String nome;
	    private String email;
	    private String senha;
	    private boolean admin = false;
	
	    public Usuario(String nome, String email, String senha) {
	        this.nome = nome;
	        this.email = email;
	        this.senha = senha;
	    }
}
