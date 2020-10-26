package br.com.doaju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.TipoEquipamento;

public interface TipoEquipamentoRepository extends JpaRepository<TipoEquipamento, Long>{
	@Query(value = "SELECT * FROM tipo_equipamento WHERE descricao = ?1", nativeQuery = true)
	public TipoEquipamento buscarPorEquipamento(String equip);
}
