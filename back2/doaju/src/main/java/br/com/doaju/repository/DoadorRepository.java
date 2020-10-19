package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.doaju.model.Doador;

public interface DoadorRepository  extends JpaRepository<Doador, Long>{

}
