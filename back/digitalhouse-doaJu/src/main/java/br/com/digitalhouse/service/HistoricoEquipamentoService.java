package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.dto.HistoricoEquipamentoDTO;
import br.com.digitalhouse.exception.EntidadeNaoEncontradaException;
import br.com.digitalhouse.mapper.HistoricoEquipamentoMapper;
import br.com.digitalhouse.model.HistoricoEquipamento;
import br.com.digitalhouse.repository.HistoricoEquipamentoRepository;
import br.com.digitalhouse.request.HistoricoEquipamentoRequest;

@Service
public class HistoricoEquipamentoService {
	@Autowired
	private HistoricoEquipamentoRepository repository;
	
	@Autowired
	private HistoricoEquipamentoMapper mapper;

	@Transactional
	public HistoricoEquipamentoDTO salvar(HistoricoEquipamentoRequest historicoEquipamentoRequest) {
		
		HistoricoEquipamento historicoEquipamento = mapper.requestToModel(historicoEquipamentoRequest);
	    return mapper.modelToDTO( repository.save(historicoEquipamento) );		
	}
	
	public Optional<HistoricoEquipamento> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Historico Equipamento não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<HistoricoEquipamentoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(hist -> mapper.modelToDTO(hist))
				.collect(Collectors.toList());	
	}

}
