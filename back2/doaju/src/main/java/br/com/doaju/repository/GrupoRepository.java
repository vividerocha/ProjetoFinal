package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

import br.com.doaju.model.Grupo;
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
	
	@Query(value = "SELECT * FROM grupo WHERE nome = ?1", nativeQuery = true)
	public Set<Grupo> localizaGrupo(String permissao);

}
