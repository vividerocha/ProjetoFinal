package br.com.digitalhouse.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalhouse.dto.DoadorDTO;
import br.com.digitalhouse.model.Doador;
import br.com.digitalhouse.request.DoadorRequest;

@Component
public class DoadorMapper {
	@Autowired
    private ModelMapper modelMapper;
	
	public DoadorDTO modelToDto(Doador doador) {
        return modelMapper.map(doador, DoadorDTO.class);
    }

    public Doador dtoRequestToModel(DoadorRequest request) {
        return modelMapper.map(request, Doador.class);
    }
}
