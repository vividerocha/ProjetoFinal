package br.com.doaju.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.doaju.model.Equipamento;
import br.com.doaju.model.HistoricoEquipamento;

public interface HistoricoEquipamentoRepository extends JpaRepository<HistoricoEquipamento, Long>{
	
	public Optional<HistoricoEquipamento> findByEquipamento(Equipamento equipamento);

}
