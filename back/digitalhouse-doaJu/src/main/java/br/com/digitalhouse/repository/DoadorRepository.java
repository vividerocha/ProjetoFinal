package br.com.digitalhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.digitalhouse.model.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {
}

