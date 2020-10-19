package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doaju.dto.TipoEquipamentoDTO;
import br.com.doaju.model.TipoEquipamento;
import br.com.doaju.request.TipoEquipamentoRequest;

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
