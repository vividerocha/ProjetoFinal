package br.com.doaju.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.AlunoDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.AlunoMapper;
import br.com.doaju.model.Aluno;
import br.com.doaju.repository.AlunoRepository;
import br.com.doaju.repository.TipoEquipamentoRepository;
import br.com.doaju.repository.UsuarioRepository;
import br.com.doaju.request.AlunoRequest;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repository;
	@Autowired
	private TipoEquipamentoRepository repoEquip;
	@Autowired
	private UsuarioRepository repoUser;
	
	@Autowired
	private AlunoMapper mapper;

	@Transactional
	public AlunoDTO salvar(AlunoRequest alunoRequest) {
		
		Aluno aluno = mapper.requestToModel(alunoRequest);		
		for (int i = 0; i < aluno.getEquipamentos().size(); i++) {
			aluno.addTipo(repoEquip.buscarPorEquipamento(alunoRequest.getEquipamentos().get(i)));
		}
		aluno.setUsuario(repoUser.findById(alunoRequest.getUsuario()).get());
		return mapper.modelToDTO( repository.save(aluno) );		
	}
	
	public AlunoDTO buscar(Long id) {
		try {
			Aluno aluno = repository.findById(id).get();
			return mapper.modelToDTO(aluno);
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
	public AlunoDTO atualizar(AlunoRequest alunoRequest) {
		Aluno aluno = mapper.requestToModel(alunoRequest);
		for (int i = 0; i < aluno.getEquipamentos().size(); i++) {
			aluno.addTipo(repoEquip.buscarPorEquipamento(alunoRequest.getEquipamentos().get(i)));
		}
		aluno.setUsuario(repoUser.findById(alunoRequest.getUsuario()).get());
		return mapper.modelToDTO(repository.save(aluno));
	}
	
	
	public AlunoDTO buscarPorIdUsuario(Long id) {
		try {
			Aluno aluno = repository.buscarPorIdUsuario(id);
			return mapper.modelToDTO(aluno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}	

}
