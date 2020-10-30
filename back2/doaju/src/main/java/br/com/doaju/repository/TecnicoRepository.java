package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Tecnico;

public interface TecnicoRepository  extends JpaRepository<Tecnico, Long>{
	@Query(value = "SELECT * FROM tecnico WHERE usuario_id = ?1", nativeQuery = true)
	public Tecnico buscarPorIdUsuario(Long id);
}
