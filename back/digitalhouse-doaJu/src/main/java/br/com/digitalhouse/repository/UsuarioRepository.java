package br.com.digitalhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.digitalhouse.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//public Usuario findByEmail(String email);
	public Usuario findByEmailEquals(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1 and u.senha = ?2")
	public Usuario findByEmailSenha(String email, String senha);
}
