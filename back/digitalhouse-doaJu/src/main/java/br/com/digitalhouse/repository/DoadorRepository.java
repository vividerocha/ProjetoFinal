package br.com.digitalhouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.digitalhouse.model.Doador;

@Repository
public interface DoadorRepository extends CrudRepository<Doador, Long> {
}

