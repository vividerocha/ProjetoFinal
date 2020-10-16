package br.com.digitalhouse.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalhouse.dto.EquipamentoDTO;
import br.com.digitalhouse.model.Equipamento;
import br.com.digitalhouse.request.EquipamentoRequest;

@Component
public class EquipamentoMapper {

	@Autowired
    private ModelMapper modelMapper;

    public Equipamento requestToModel(EquipamentoRequest equipamentoRequest) {
        return modelMapper.map(equipamentoRequest, Equipamento.class);
    }
    
    public EquipamentoDTO modelToDTO(Equipamento equipamento) {
        return modelMapper.map(equipamento, EquipamentoDTO.class);
    }
}
