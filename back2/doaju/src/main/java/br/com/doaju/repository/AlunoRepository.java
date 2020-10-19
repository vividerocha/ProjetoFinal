package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.doaju.model.Aluno;

public interface AlunoRepository  extends JpaRepository<Aluno, Long>{

}
