package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.model.Tecnico;
import br.com.digitalhouse.repository.TecnicoRepository;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    @Autowired
    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico salvar(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }
    
    public List<Tecnico> listar() {
		return tecnicoRepository.findAll();	
	}
    
    public Optional<Tecnico> buscar(Long id) {
		return tecnicoRepository.findById(id);
	}
    
    @Transactional
	public void excluir(Long id) {
		
		try {
			tecnicoRepository.deleteById(id);
			tecnicoRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			
		};			
	}
    
    @Transactional
	public void atualizar(Tecnico tecnico) {
				
		tecnicoRepository.save(tecnico);		
	}
}