package br.com.doaju.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{
	
	@Query("Select a from Equipamento a left join HistoricoEquipamento b on a.id = b.equipamento WHERE b.usuario.id = ?1")
	public List<Equipamento> BuscaEquipamentosDoador(Long idDoador);

}
