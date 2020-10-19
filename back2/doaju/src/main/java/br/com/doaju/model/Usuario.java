package br.com.doaju.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
	    
		@Temporal(TemporalType.TIMESTAMP)     
		private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
	
//	    public Usuario(String nome, String email, String senha) {
//	        this.nome = nome;
//	        this.email = email;
//	        this.senha = senha;
//	    }
}
