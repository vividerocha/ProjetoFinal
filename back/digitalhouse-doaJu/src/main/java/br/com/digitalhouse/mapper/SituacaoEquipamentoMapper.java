package br.com.digitalhouse.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalhouse.dto.SituacaoEquipamentoDTO;
import br.com.digitalhouse.model.SituacaoEquipamento;
import br.com.digitalhouse.request.SituacaoEquipamentoRequest;

@Component
public class SituacaoEquipamentoMapper {
	@Autowired
    private ModelMapper modelMapper;

    public SituacaoEquipamento requestToModel(SituacaoEquipamentoRequest situacaoEquipamentoRequest) {
        return modelMapper.map(situacaoEquipamentoRequest, SituacaoEquipamento.class);
    }
    
    public SituacaoEquipamentoDTO modelToDTO(SituacaoEquipamento situacaoEquipamento) {
        return modelMapper.map(situacaoEquipamento, SituacaoEquipamentoDTO.class);
    }
}
