package br.com.digitalhouse.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.dto.UsuarioDTO;
import br.com.digitalhouse.mapper.UsuarioMapper;
import br.com.digitalhouse.model.Usuario;
import br.com.digitalhouse.repository.UsuarioRepository;
import br.com.digitalhouse.request.UsuarioRequest;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioMapper mapper;

	public UsuarioDTO salvar(UsuarioRequest usuario) {
		Usuario user = mapper.dtoRequestToModel(usuario);
		Usuario userMail = buscarEmail(user.getEmail());
		if (userMail == null) {
			System.out.println("voltou nulo");
			return mapper.modelToDto(repository.save(user));
		}
		return null;		
	}

	public Usuario buscarEmail(String email) {
		return repository.findByEmailEquals(email);

	}
	
	public Usuario login(String email, String senha) {
		return repository.findByEmailSenha(email, senha);

	}

//    private final UsuarioRepository usuarioRepository;
//
//    @Autowired
//    public UsuarioService(UsuarioRepository usuarioRepository) {
//        this.usuarioRepository = usuarioRepository;
//    }
//
//    public Usuario salvar(Usuario usuario) {
//        return usuarioRepository.save(usuario);
//    }
//    
//    public List<Usuario> listar() {
//		return usuarioRepository.findAll();	
//	}
//    
    public Optional<Usuario> buscarPorId(Long id) {
		return repository.findById(id);
	}
//    
//    public Usuario buscarEmail(String email) {
//    	return usuarioRepository.findByEmailEquals(email);
//		
//	}
//    
//    @Transactional
//	public void excluir(Long id) {
//		
//		try {
//			usuarioRepository.deleteById(id);
//			usuarioRepository.flush();
//		
//		} catch (EmptyResultDataAccessException e) {
//			
//		};			
//	}
//    
//    @Transactional
//	public void atualizar(Usuario usuario) {
//				
//		usuarioRepository.save(usuario);		
//	}
}
