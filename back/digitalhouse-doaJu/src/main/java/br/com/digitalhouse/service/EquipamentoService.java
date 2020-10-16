package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.dto.EquipamentoDTO;
import br.com.digitalhouse.exception.EntidadeNaoEncontradaException;
import br.com.digitalhouse.mapper.EquipamentoMapper;
import br.com.digitalhouse.model.Equipamento;
import br.com.digitalhouse.repository.EquipamentoRepository;
import br.com.digitalhouse.request.EquipamentoRequest;

@Service
public class EquipamentoService {
	@Autowired
	private EquipamentoRepository repository;
	
	@Autowired
	private EquipamentoMapper mapper;

	@Transactional
	public EquipamentoDTO salvar(EquipamentoRequest equipamentoRequest) {
		
		Equipamento equipamento = mapper.requestToModel(equipamentoRequest);
	    return mapper.modelToDTO( repository.save(equipamento) );		
	}
	
	public Optional<Equipamento> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Equipamento não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<EquipamentoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(equi -> mapper.modelToDTO(equi))
				.collect(Collectors.toList());	
	}
}
