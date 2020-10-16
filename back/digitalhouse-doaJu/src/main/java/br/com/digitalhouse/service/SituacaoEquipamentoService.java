package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.dto.SituacaoEquipamentoDTO;
import br.com.digitalhouse.exception.EntidadeNaoEncontradaException;
import br.com.digitalhouse.mapper.SituacaoEquipamentoMapper;
import br.com.digitalhouse.model.SituacaoEquipamento;
import br.com.digitalhouse.repository.SituacaoEquipamentoRepository;
import br.com.digitalhouse.request.SituacaoEquipamentoRequest;

@Service
public class SituacaoEquipamentoService {
	@Autowired
	private SituacaoEquipamentoRepository repository;
	
	@Autowired
	private SituacaoEquipamentoMapper mapper;

	@Transactional
	public SituacaoEquipamentoDTO salvar(SituacaoEquipamentoRequest situacaoEquipamentoRequest) {
		
		SituacaoEquipamento situacaoEquipamento = mapper.requestToModel(situacaoEquipamentoRequest);
	    return mapper.modelToDTO( repository.save(situacaoEquipamento) );		
	}
	
	public Optional<SituacaoEquipamento> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Situacao Equipamento não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<SituacaoEquipamentoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(situ -> mapper.modelToDTO(situ))
				.collect(Collectors.toList());	
	}

}
