package br.com.doaju.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
import br.com.doaju.repository.UsuarioRepository;
import br.com.doaju.request.DoadorRequest;

@Service
public class DoadorService {
	@Autowired
	private DoadorRepository repository;
	
	@Autowired
	private DoadorMapper mapper;
	
	@Autowired
	private UsuarioRepository repoUser;

	@Transactional
	public DoadorDTO salvar(DoadorRequest doadorRequest) {
		
		Doador doador = mapper.requestToModel(doadorRequest);
		doador.setUsuario((repoUser.findById(doadorRequest.getUsuario()).get()));
		System.out.println(doador.toString());
	    return mapper.modelToDTO( repository.save(doador) );		
	}
	
	public DoadorDTO buscar(Long id) {
		try {
			Doador doador = repository.findById(id).get(); 
			return mapper.modelToDTO(doador);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public DoadorDTO buscarPorIdUsuario(Long id) {
		try {
			Doador doador = repository.buscarPorIdUsuario(id);
			return mapper.modelToDTO(doador);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
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
	
	@Transactional
	public DoadorDTO atualizar(DoadorRequest doadorRequest) {
		Doador doador = mapper.requestToModel(doadorRequest);
		return mapper.modelToDTO( repository.save(doador) );		
	}

}
