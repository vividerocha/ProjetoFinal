package br.com.digitalhouse.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalhouse.dto.TipoEquipamentoDTO;
import br.com.digitalhouse.model.TipoEquipamento;
import br.com.digitalhouse.request.TipoEquipamentoRequest;

@Component
public class TipoEquipamentoMapper {
	@Autowired
    private ModelMapper modelMapper;

    public TipoEquipamento requestToModel(TipoEquipamentoRequest tipoEquipamentoRequest) {
        return modelMapper.map(tipoEquipamentoRequest, TipoEquipamento.class);
    }
    
    public TipoEquipamentoDTO modelToDTO(TipoEquipamento tipoEquipamento) {
        return modelMapper.map(tipoEquipamento, TipoEquipamentoDTO.class);
    }
    
    
}
