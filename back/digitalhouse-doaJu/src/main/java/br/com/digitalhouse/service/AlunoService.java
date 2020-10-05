package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.model.Aluno;
import br.com.digitalhouse.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    
    public List<Aluno> listar() {
		return alunoRepository.findAll();	
	}
    
    public Optional<Aluno> buscar(Long id) {
		return alunoRepository.findById(id);
	}
    
    @Transactional
	public void excluir(Long id) {
		
		try {
			alunoRepository.deleteById(id);
			alunoRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			
		};			
	}
    
    @Transactional
	public void atualizar(Aluno aluno) {
				
    	alunoRepository.save(aluno);		
	}
}