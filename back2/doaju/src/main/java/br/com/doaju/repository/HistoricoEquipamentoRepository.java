package br.com.doaju.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Equipamento;
import br.com.doaju.model.EquipamentosTecnicoRegiao;
import br.com.doaju.model.HistoricoEquipamento;

public interface HistoricoEquipamentoRepository extends JpaRepository<HistoricoEquipamento, Long>{
	
	public Optional<HistoricoEquipamento> findByEquipamento(Equipamento equipamento);
	
	@Query("select a.descricao as tipoEquipamento, b.descricaoEquipamento, b.funcionando,  " +
			" s.situacao, e.nomeCompleto as nomeDoador, e.telefone as telefoneDoador, e.celular as celularDoador, " +
			" e.logradouro as enderecoDoador, e.estado as estadoDoador, e.numeroCasa as casaDoador " +
			" from TipoEquipamento a left join Equipamento b " +		
			" left join HistoricoEquipamento c on b.id = c.equipamento.id" + 
			" left join SituacaoEquipamento s "
			+ " left join Usuario d " +
			" left join Doador e where b.funcionando = 0 and e.estado = ?1 ")
	public List<EquipamentosTecnicoRegiao> buscaEquipamentosParaReparoPorRegiao(String regiao);
		
}
