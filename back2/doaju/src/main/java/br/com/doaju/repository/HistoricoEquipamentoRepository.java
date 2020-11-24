package br.com.doaju.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Aluno;
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
			" where e.estado = ?1 and s.id = 1")
	public List<EquipamentosTecnicoRegiao> buscaEquipamentosParaReparoPorRegiao(String regiao);
	
	@Query(value="select situacao_equipamento_id from historico_equipamento where equipamento_id = :id order by data_alteracao desc limit 1", nativeQuery = true)
	public Long buscaUltimaSituacao(Long id);
	
	@Query("select new br.com.doaju.model.EquipamentosTecnicoRegiao(b.id as idEquipamento, a.descricao as tipoEquipamento, b.descricaoEquipamento, b.funcionando,  " +
			" s.situacao, e.nomeCompleto as nomeDoador, e.telefone as telefoneDoador, e.celular as celularDoador, " +
			" e.logradouro as enderecoDoador, e.cidade as cidade, e.estado as estadoDoador, e.numeroCasa as casaDoador) " +
			" from TipoEquipamento a " +
			" join Equipamento b on a.id = b.tipoEquipamento.id" +		
			" join HistoricoEquipamento c on b.id = c.equipamento.id" + 
			" join SituacaoEquipamento s on s.id = c.situacaoEquipamento.id"
			+ " left join Usuario d on d.id = c.usuario.id" +
			" left join Tecnico e on e.usuario.id = d.id" +
			" where e.estado = :regiao and s.id = 4 and b.id not in (:equipamentosDistribuidos) ")
	public List<EquipamentosTecnicoRegiao> buscaEquipamentosParaDistribuicaoPorRegiao(String regiao, List<Long> equipamentosDistribuidos);
	
	@Query("select a.usuario.id from HistoricoEquipamento a")
	public List<Long> buscaAlunosContemplados();
	
	@Query("select a.equipamento.id from HistoricoEquipamento a where a.situacaoEquipamento.id = 5")
	public List<Long> buscaEquipamentosDistribuidos();
	
	@Query("select new br.com.doaju.model.EquipamentosTecnicoRegiao(b.id as idEquipamento, a.descricao as tipoEquipamento, b.descricaoEquipamento, b.funcionando,  " +
			" s.situacao, e.nomeCompleto as nomeDoador, e.telefone as telefoneDoador, e.celular as celularDoador, " +
			" e.logradouro as enderecoDoador, e.cidade as cidade, e.estado as estadoDoador, e.numeroCasa as casaDoador) " +
			" from TipoEquipamento a " +
			" join Equipamento b on a.id = b.tipoEquipamento.id" +		
			" join HistoricoEquipamento c on b.id = c.equipamento.id" + 
			" join SituacaoEquipamento s on s.id = c.situacaoEquipamento.id"
			+ " left join Usuario d on d.id = c.usuario.id" +
			" left join Aluno e on e.usuario.id = d.id" +
			" where s.id = 5 and e.usuario.id = :idAluno ")
	public EquipamentosTecnicoRegiao buscaEquipamentoDistribuido(Long idAluno);
	
	@Query("select new br.com.doaju.model.EquipamentosTecnicoRegiao(b.id as idEquipamento, a.descricao as tipoEquipamento, b.descricaoEquipamento, b.funcionando,  " +
			" s.situacao, e.nomeCompleto as nomeDoador, e.telefone as telefoneDoador, e.celular as celularDoador, " +
			" e.logradouro as enderecoDoador, e.cidade as cidade, e.estado as estadoDoador, e.numeroCasa as casaDoador) " +
			" from TipoEquipamento a " +
			" join Equipamento b on a.id = b.tipoEquipamento.id" +		
			" join HistoricoEquipamento c on b.id = c.equipamento.id" + 
			" join SituacaoEquipamento s on s.id = c.situacaoEquipamento.id"
			+ " left join Usuario d on d.id = c.usuario.id" +
			" left join Tecnico e on e.usuario.id = d.id" +
			" where s.id = 4 and b.id = :idEquipamento ")
	public EquipamentosTecnicoRegiao buscaTecnico(Long idEquipamento);
		
}
