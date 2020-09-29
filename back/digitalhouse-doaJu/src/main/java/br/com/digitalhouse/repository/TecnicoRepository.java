package br.com.digitalhouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.digitalhouse.model.Tecnico;

@Repository
public interface TecnicoRepository extends CrudRepository<Tecnico, Long> {
}
