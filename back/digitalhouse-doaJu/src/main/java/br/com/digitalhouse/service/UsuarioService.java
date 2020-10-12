package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.model.Usuario;
import br.com.digitalhouse.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listar() {
		return usuarioRepository.findAll();	
	}
    
    public Optional<Usuario> buscar(Long id) {
		return usuarioRepository.findById(id);
	}
    
    public Usuario buscarEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
    
    @Transactional
	public void excluir(Long id) {
		
		try {
			usuarioRepository.deleteById(id);
			usuarioRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			
		};			
	}
    
    @Transactional
	public void atualizar(Usuario usuario) {
				
		usuarioRepository.save(usuario);		
	}
}
