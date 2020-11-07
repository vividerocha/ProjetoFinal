package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Questionario;

public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {
	
	@Query("Select a from Questionario a where aluno_id = ?1")
	public Questionario buscarPorIdAluno(Long id);

}
