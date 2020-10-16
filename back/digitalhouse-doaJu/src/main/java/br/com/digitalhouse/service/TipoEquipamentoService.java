package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.dto.TipoEquipamentoDTO;
import br.com.digitalhouse.exception.EntidadeNaoEncontradaException;
import br.com.digitalhouse.mapper.TipoEquipamentoMapper;
import br.com.digitalhouse.model.TipoEquipamento;
import br.com.digitalhouse.repository.TipoEquipamentoRepository;
import br.com.digitalhouse.request.TipoEquipamentoRequest;

@Service
public class TipoEquipamentoService {
	@Autowired
	private TipoEquipamentoRepository repository;
	
	@Autowired
	private TipoEquipamentoMapper mapper;

	@Transactional
	public TipoEquipamentoDTO salvar(TipoEquipamentoRequest tipoEquipamentoRequest) {
		
		TipoEquipamento tipoEquipamento = mapper.requestToModel(tipoEquipamentoRequest);
	    return mapper.modelToDTO( repository.save(tipoEquipamento) );		
	}
	
	public Optional<TipoEquipamento> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Tipo Equipamento não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<TipoEquipamentoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(equi -> mapper.modelToDTO(equi))
				.collect(Collectors.toList());	
	}

}
