package br.com.doaju.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doaju.model.Ranking;
import br.com.doaju.model.Aluno;
import br.com.doaju.model.Questionario;

public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {
	
	@Query("Select a from Questionario a where aluno_id = ?1")
	public Questionario buscarPorIdAluno(Long id);
	
	@Query("select new br.com.doaju.model.Ranking(e.id as idAluno, e.nomeCompleto as nomeAluno, e.estado as regiaoAluno, q.pontuacaoTotal as pontuacaoAluno) " +
			" from Usuario d " +
			" join Aluno e on e.usuario.id = d.id " +
			" join Questionario q on q.id = e.id " +
			" where e.estado = :regiao " +
			" and e.usuario.id not in (:alunosContemplados) order by q.pontuacaoTotal desc"
			)
	public List<Ranking> montaRankingPorRegiao(String regiao, List<Long> alunosContemplados);

}
