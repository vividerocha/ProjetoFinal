package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.model.Doador;
import br.com.digitalhouse.repository.DoadorRepository;

@Service
public class DoadorService {

    private final DoadorRepository doadorRepository;

    @Autowired
    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }

    public Doador salvar(Doador doador) {
        return doadorRepository.save(doador);
    }
    
    public List<Doador> listar() {
		return doadorRepository.findAll();	
	}
    
    public Optional<Doador> buscar(Long id) {
		return doadorRepository.findById(id);
	}
    
    @Transactional
	public void excluir(Long id) {
		
		try {
			doadorRepository.deleteById(id);
			doadorRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			
		};			
	}
    
    @Transactional
	public void atualizar(Doador doador) {
				
		doadorRepository.save(doador);		
	}
}