package br.com.digitalhouse.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalhouse.dto.HistoricoEquipamentoDTO;
import br.com.digitalhouse.model.HistoricoEquipamento;
import br.com.digitalhouse.request.HistoricoEquipamentoRequest;

@Component
public class HistoricoEquipamentoMapper {
	@Autowired
    private ModelMapper modelMapper;

    public HistoricoEquipamento requestToModel(HistoricoEquipamentoRequest historicoEquipamentoRequest) {
        return modelMapper.map(historicoEquipamentoRequest, HistoricoEquipamento.class);
    }
    
    public HistoricoEquipamentoDTO modelToDTO(HistoricoEquipamento historicoEquipamento) {
        return modelMapper.map(historicoEquipamento, HistoricoEquipamentoDTO.class);
    }

}
