package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
import br.com.doaju.dto.HistoricoEquipamentoDTO;
import br.com.doaju.model.EquipamentosTecnicoRegiao;
//import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
import br.com.doaju.model.HistoricoEquipamento;
//import br.com.doaju.model.EquipamentosTecnicoRegiao;
import br.com.doaju.request.HistoricoEquipamentoRequest;

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
    
    public EquipamentosTecnicoRegiaoDTO modelToEqDTO(EquipamentosTecnicoRegiao equipamentoTecnico) {
        return modelMapper.map(equipamentoTecnico, EquipamentosTecnicoRegiaoDTO.class);
    }

}
