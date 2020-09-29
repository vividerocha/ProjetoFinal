package br.com.digitalhouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.digitalhouse.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}
