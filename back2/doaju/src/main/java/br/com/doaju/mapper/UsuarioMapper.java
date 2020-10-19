package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doaju.dto.UsuarioDTO;
import br.com.doaju.model.Usuario;
import br.com.doaju.request.UsuarioRequest;

@Component
public class UsuarioMapper {
	@Autowired
    private ModelMapper modelMapper;
	
	public UsuarioDTO modelToDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario dtoRequestToModel(UsuarioRequest request) {
        return modelMapper.map(request, Usuario.class);
    }
}
