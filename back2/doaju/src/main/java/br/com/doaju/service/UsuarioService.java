package br.com.doaju.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.UsuarioDTO;
import br.com.doaju.mapper.UsuarioMapper;
import br.com.doaju.model.Grupo;
import br.com.doaju.model.Usuario;
import br.com.doaju.repository.GrupoRepository;
import br.com.doaju.repository.UsuarioRepository;
import br.com.doaju.request.UsuarioRequest;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private GrupoRepository gruporepository;
	@Autowired
	private UsuarioMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioDTO salvar(UsuarioRequest usuario) {
		
		Usuario user = mapper.dtoRequestToModel(usuario);
		user.setSenha(passwordEncoder.encode(usuario.getSenha()));
		user.setGrupos(gruporepository.localizaGrupo(usuario.getTipoPermissao()));
		
		Usuario userMail = buscarEmail(user.getEmail());
		Usuario userUser = buscarUsuario(user.getUsuario());
		if (userMail == null) {			
			System.out.println(user.toString());
			return mapper.modelToDto(repository.save(user));
		}else if(userUser == null) {
			System.out.println(user.toString());
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
	
	public Usuario buscarUsuario(String user) {
		return repository.buscarPorUsuario(user);
	}
	
	public Grupo buscarIdGrupoAdmin(String desc) {
		return gruporepository.buscaIdAdmin(desc);
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
	
    public List<Usuario> listar() {
		return repository.findAll();	
	}
    
    public Optional<Usuario> buscarPorId(Long id) {
		return repository.findById(id);
	}
//    
//    public Usuario buscarEmail(String email) {
//    	return usuarioRepository.findByEmailEquals(email);
//		
//	}
//    
    @Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (Exception e) {
			
		};			
	}
    
    @Transactional
	public UsuarioDTO atualizar(UsuarioRequest usuarioRequest) {
		Usuario user = mapper.dtoRequestToModel(usuarioRequest);
		user.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
		//user.setGrupos(gruporepository.localizaGrupo(usuarioRequest.getTipoPermissao()));
		return mapper.modelToDto(repository.save(user));
	}
}
