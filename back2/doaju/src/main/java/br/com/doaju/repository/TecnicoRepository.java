package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.doaju.model.Tecnico;

public interface TecnicoRepository  extends JpaRepository<Tecnico, Long>{

}
