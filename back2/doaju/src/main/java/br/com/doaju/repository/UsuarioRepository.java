package br.com.doaju.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.doaju.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//public Usuario findByEmail(String email);
	public Usuario findByEmailEquals(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1 and u.senha = ?2")
	public Usuario findByEmailSenha(String email, String senha);
	
	Optional<Usuario> findByEmail(String email);
	
	@Query(value = "SELECT * FROM usuario WHERE usuario = ?1", nativeQuery = true)
	public Usuario buscarPorUsuario(String user);
}
