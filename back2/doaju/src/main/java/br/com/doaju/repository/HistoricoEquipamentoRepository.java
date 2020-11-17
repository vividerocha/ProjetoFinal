package br.com.doaju.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Equipamento;
import br.com.doaju.model.EquipamentosTecnicoRegiao;
//import br.com.doaju.model.EquipamentosTecnicoRegiao;
import br.com.doaju.model.HistoricoEquipamento;

public interface HistoricoEquipamentoRepository extends JpaRepository<HistoricoEquipamento, Long>{
	
	@Query("from HistoricoEquipamento a where a.equipamento.id = :id")
	public List<HistoricoEquipamento> findByEquipamento(Long id);
	
	@Query("select new br.com.doaju.model.EquipamentosTecnicoRegiao(b.id as idEquipamento, a.descricao as tipoEquipamento, b.descricaoEquipamento, b.funcionando,  " +
			" s.situacao, e.nomeCompleto as nomeDoador, e.telefone as telefoneDoador, e.celular as celularDoador, " +
			" e.logradouro as enderecoDoador, e.cidade as cidade, e.estado as estadoDoador, e.numeroCasa as casaDoador) " +
			" from TipoEquipamento a " +
			" join Equipamento b on a.id = b.tipoEquipamento.id" +		
			" left join HistoricoEquipamento c on b.id = c.equipamento.id" + 
			" left join SituacaoEquipamento s on s.id = c.situacaoEquipamento.id"
			+ " left join Usuario d on d.id = c.usuario.id" +
			" left join Doador e on e.usuario.id = d.id" +
			" where b.funcionando = 0 and e.estado = ?1 and s.id = 1")
	public List<EquipamentosTecnicoRegiao> buscaEquipamentosParaReparoPorRegiao(String regiao);
	
	@Query(value="select situacao_equipamento_id from Historico_Equipamento where equipamento_id = :id order by data_alteracao desc limit 1", nativeQuery = true)
	public Long buscaUltimaSituacao(Long id);
		
}
