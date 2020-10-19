package br.com.doaju.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.DoadorDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.DoadorMapper;
import br.com.doaju.model.Doador;
import br.com.doaju.repository.DoadorRepository;
import br.com.doaju.request.DoadorRequest;

@Service
public class DoadorService {
	@Autowired
	private DoadorRepository repository;
	
	@Autowired
	private DoadorMapper mapper;

	@Transactional
	public DoadorDTO salvar(DoadorRequest doadorRequest) {
		
		Doador doador = mapper.requestToModel(doadorRequest);
	    return mapper.modelToDTO( repository.save(doador) );		
	}
	
	public Optional<Doador> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Doador não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<DoadorDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(doa -> mapper.modelToDTO(doa))
				.collect(Collectors.toList());	
	}

}
