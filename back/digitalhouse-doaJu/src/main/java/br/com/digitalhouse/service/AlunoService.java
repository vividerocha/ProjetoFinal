package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.dto.AlunoDTO;
import br.com.digitalhouse.exception.EntidadeNaoEncontradaException;
import br.com.digitalhouse.mapper.AlunoMapper;
import br.com.digitalhouse.model.Aluno;
import br.com.digitalhouse.repository.AlunoRepository;
import br.com.digitalhouse.request.AlunoRequest;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private AlunoMapper mapper;

	@Transactional
	public AlunoDTO salvar(AlunoRequest alunoRequest) {
		
		Aluno aluno = mapper.requestToModel(alunoRequest);
	    return mapper.modelToDTO( repository.save(aluno) );		
	}
	
	public Optional<Aluno> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Aluno não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<AlunoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(alu -> mapper.modelToDTO(alu))
				.collect(Collectors.toList());	
	}
	
	@Transactional
	public void atualizar(Aluno aluno) {
		repository.save(aluno);		
	}

}
