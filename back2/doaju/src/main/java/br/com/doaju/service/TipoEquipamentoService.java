package br.com.doaju.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.doaju.dto.TipoEquipamentoDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.TipoEquipamentoMapper;
import br.com.doaju.model.TipoEquipamento;
import br.com.doaju.repository.TipoEquipamentoRepository;
import br.com.doaju.request.TipoEquipamentoRequest;

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
	
	public TipoEquipamentoDTO buscar(Long id) {
		return mapper.modelToDTO(repository.findById(id).get());
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
	
	public TipoEquipamento buscarTipo(String desc) {
		return repository.buscarPorEquipamento(desc);
	}
	
	@Transactional
	public TipoEquipamentoDTO atualizar(TipoEquipamentoRequest tipoRequest) {
		TipoEquipamento tipo = mapper.requestToModel(tipoRequest);
		return mapper.modelToDTO( repository.save(tipo) );		
	}
	

}
