package br.com.digitalhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digitalhouse.model.Doador;

public interface DoadorRepository  extends JpaRepository<Doador, Long>{

}
