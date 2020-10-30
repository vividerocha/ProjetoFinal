package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Doador;

public interface DoadorRepository  extends JpaRepository<Doador, Long>{
	
	@Query(value = "SELECT * FROM doador WHERE usuario_id = ?1", nativeQuery = true)
	public Doador buscarPorIdUsuario(Long user);

}
