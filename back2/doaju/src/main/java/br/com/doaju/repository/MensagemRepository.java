package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.doaju.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

}
