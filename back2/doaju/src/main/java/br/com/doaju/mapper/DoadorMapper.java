package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doaju.dto.DoadorDTO;
import br.com.doaju.model.Doador;
import br.com.doaju.request.DoadorRequest;

@Component
public class DoadorMapper {
	@Autowired
    private ModelMapper modelMapper;

    public Doador requestToModel(DoadorRequest doadorRequest) {
        return modelMapper.map(doadorRequest, Doador.class);
    }
    
    public DoadorDTO modelToDTO(Doador doador) {
        return modelMapper.map(doador, DoadorDTO.class);
    }

}
