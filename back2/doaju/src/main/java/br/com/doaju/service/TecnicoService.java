package br.com.doaju.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.TecnicoDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.TecnicoMapper;
import br.com.doaju.model.Tecnico;
import br.com.doaju.repository.TecnicoRepository;
import br.com.doaju.request.TecnicoRequest;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private TecnicoMapper mapper;

	@Transactional
	public TecnicoDTO salvar(TecnicoRequest tecnicoRequest) {
		
		Tecnico tecnico = mapper.requestToModel(tecnicoRequest);
	    return mapper.modelToDTO( repository.save(tecnico) );		
	}
	
	public Optional<Tecnico> buscar(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Tecnico não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<TecnicoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(tec -> mapper.modelToDTO(tec))
				.collect(Collectors.toList());	
	}

}
