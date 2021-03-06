package br.com.doaju.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.doaju.dto.EquipamentoDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.EquipamentoMapper;
import br.com.doaju.model.Equipamento;
import br.com.doaju.repository.EquipamentoRepository;
import br.com.doaju.request.EquipamentoRequest;

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
	
	public EquipamentoDTO buscar(Long id) {
		Equipamento equipamento = repository.findById(id).get(); 
		return mapper.modelToDTO(equipamento);
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
				.map(doa -> mapper.modelToDTO(doa))
				.collect(Collectors.toList()) ;	
	}
	
	@Transactional
	public EquipamentoDTO atualizar(EquipamentoRequest equipamentoRequest) {
		Equipamento equipamento = mapper.requestToModel(equipamentoRequest);
		return mapper.modelToDTO( repository.save(equipamento) );		
	}
	
	public List<EquipamentoDTO> buscarPorDoador(Long idDoador) {
		return repository.BuscaEquipamentosDoador(idDoador)
				.stream()
				.map(doa -> mapper.modelToDTO(doa))
				.collect(Collectors.toList()) ;	
	}
	
}
