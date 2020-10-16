package br.com.digitalhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digitalhouse.model.Aluno;

public interface AlunoRepository  extends JpaRepository<Aluno, Long>{

}
