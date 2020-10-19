package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doaju.dto.TecnicoDTO;
import br.com.doaju.model.Tecnico;
import br.com.doaju.request.TecnicoRequest;

@Component
public class TecnicoMapper {
	@Autowired
    private ModelMapper modelMapper;

    public Tecnico requestToModel(TecnicoRequest tecnicoRequest) {
        return modelMapper.map(tecnicoRequest, Tecnico.class);
    }
    
    public TecnicoDTO modelToDTO(Tecnico tecnico) {
        return modelMapper.map(tecnico, TecnicoDTO.class);
    }

}
