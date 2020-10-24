package br.com.doaju.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // JPA Only
public class Usuario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String usuario;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Transient
	private String tipoPermissao;
	
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	

//	    @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//		@Column(name = "idUsuario")
//	    private Long id;
//	    private String nome;
//	    private String email;
//	    private String senha;
//	    private boolean admin = false;
//	    
//		@Temporal(TemporalType.TIMESTAMP)     
//		private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
	
//	    public Usuario(String nome, String email, String senha) {
//	        this.nome = nome;
//	        this.email = email;
//	        this.senha = senha;
//	    }
}
