package br.com.doaju.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.MensagemDTO;
import br.com.doaju.mapper.MensagemMapper;
import br.com.doaju.model.Mensagem;
import br.com.doaju.repository.MensagemRepository;
import br.com.doaju.request.MensagemRequest;

@Service
public class MensagemService {
	@Autowired
	private MensagemRepository repository;
	
	@Autowired
	private MensagemMapper mapper;

	@Transactional
	public MensagemDTO salvar(MensagemRequest mensagemRequest) {
		
		Mensagem mensagem = mapper.requestToModel(mensagemRequest);		
		return mapper.modelToDTO( repository.save(mensagem) );		
	}
	
	public Optional<Mensagem> buscar(Long id) {
		return repository.findById(id);
	}

	public List<MensagemDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(alu -> mapper.modelToDTO(alu))
				.collect(Collectors.toList());	
	}
}
