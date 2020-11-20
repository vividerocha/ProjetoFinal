package br.com.doaju.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.QuestionarioDTO;
import br.com.doaju.dto.RankingDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.QuestionarioMapper;
import br.com.doaju.model.Aluno;
import br.com.doaju.model.Questionario;
import br.com.doaju.repository.AlunoRepository;
import br.com.doaju.repository.HistoricoEquipamentoRepository;
import br.com.doaju.repository.QuestionarioRepository;
import br.com.doaju.request.QuestionarioRequest;

@Service
public class QuestionarioService {
	
	@Autowired
	private QuestionarioRepository repository;
	
	@Autowired
	private HistoricoEquipamentoRepository repositoryH;
	
	@Autowired
	private QuestionarioMapper mapper;
	
	@Autowired
	private AlunoRepository repoAluno;

	@Transactional
	public QuestionarioDTO salvar(QuestionarioRequest questionarioRequest) {
		
		Questionario questionario = mapper.requestToModel(questionarioRequest);		
		Aluno aluno = repoAluno.findById(questionarioRequest.getAluno()).get();
		questionario.setAluno(aluno);		
		return mapper.modelToDTO(repository.save(questionario));		
	}
	
	public QuestionarioDTO buscar(Long id) {
		try {
			Questionario questionario = repository.findById(id).get();
			return mapper.modelToDTO(questionario);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Questionario não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<QuestionarioDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(ques -> mapper.modelToDTO(ques))
				.collect(Collectors.toList());	
	}
	
	public List<RankingDTO> buscarRankingRegiao(String regiao){
		List<Long> alunos = repositoryH.buscaAlunosContemplados();
		return repository.montaRankingPorRegiao(regiao, alunos)
				.stream()
				.map(rank -> mapper.modelToRankDTO(rank))
				.collect(Collectors.toList());	
	}
	
	@Transactional
	public QuestionarioDTO atualizar(QuestionarioRequest questionarioRequest) {
		Questionario questionario = mapper.requestToModel(questionarioRequest);
		Aluno aluno = repoAluno.findById(questionarioRequest.getAluno()).get();
		questionario.setAluno(aluno);
		return mapper.modelToDTO(repository.save(questionario));
	}
	
	public QuestionarioDTO buscarPorIdAluno(Long id) {
		try {
			Questionario questionario = repository.buscarPorIdAluno(id);
			return mapper.modelToDTO(questionario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
}
