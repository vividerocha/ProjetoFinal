package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.SituacaoEquipamento;

public interface SituacaoEquipamentoRepository extends JpaRepository<SituacaoEquipamento, Long>{
	@Query(value = "SELECT * FROM situacao_equipamento WHERE descricao = ?1", nativeQuery = true)
	public SituacaoEquipamento buscarPorEquipamento(String equip);
}
