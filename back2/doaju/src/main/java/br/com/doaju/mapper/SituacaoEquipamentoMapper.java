package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doaju.dto.SituacaoEquipamentoDTO;
import br.com.doaju.model.SituacaoEquipamento;
import br.com.doaju.request.SituacaoEquipamentoRequest;

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
