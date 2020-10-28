package br.com.doaju.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.doaju.dto.SituacaoEquipamentoDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.SituacaoEquipamentoMapper;
import br.com.doaju.model.SituacaoEquipamento;
import br.com.doaju.repository.SituacaoEquipamentoRepository;
import br.com.doaju.request.SituacaoEquipamentoRequest;


@Service
public class SituacaoEquipamentoService {
	@Autowired
	private SituacaoEquipamentoRepository repository;
	
	@Autowired
	private SituacaoEquipamentoMapper mapper;

	@Transactional
	public SituacaoEquipamentoDTO salvar(SituacaoEquipamentoRequest situacaoRequest) {
		
		SituacaoEquipamento situacao = mapper.requestToModel(situacaoRequest);
	    return mapper.modelToDTO( repository.save(situacao) );		
	}
	
	public SituacaoEquipamentoDTO buscar(Long id) {
		return mapper.modelToDTO(repository.findById(id).get());
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Situação Equipamento não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<SituacaoEquipamentoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(equi -> mapper.modelToDTO(equi))
				.collect(Collectors.toList());	
	}
	
	public SituacaoEquipamento buscarTipo(String desc) {
		return repository.buscarPorEquipamento(desc);
	}
	
	@Transactional
	public SituacaoEquipamentoDTO atualizar(SituacaoEquipamentoRequest situacaoRequest) {
		SituacaoEquipamento situacao = mapper.requestToModel(situacaoRequest);
		return mapper.modelToDTO( repository.save(situacao) );		
	}

}
