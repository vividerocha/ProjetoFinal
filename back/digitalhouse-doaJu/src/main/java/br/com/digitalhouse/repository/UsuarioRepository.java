package br.com.digitalhouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.digitalhouse.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
