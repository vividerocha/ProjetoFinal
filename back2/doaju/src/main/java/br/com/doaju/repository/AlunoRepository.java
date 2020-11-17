package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Aluno;

public interface AlunoRepository  extends JpaRepository<Aluno, Long>{
	@Query(value = "SELECT * FROM aluno WHERE usuario_id = ?1", nativeQuery = true)
	public Aluno buscarPorIdUsuario(Long id);
	
	@Query(value = "SELECT a FROM Aluno a WHERE usuario_id = ?1")
	public Aluno buscaPorIdUsuario(Long id);
	
	
}
