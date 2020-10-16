package br.com.digitalhouse.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalhouse.dto.TecnicoDTO;
import br.com.digitalhouse.model.Tecnico;
import br.com.digitalhouse.request.TecnicoRequest;

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
