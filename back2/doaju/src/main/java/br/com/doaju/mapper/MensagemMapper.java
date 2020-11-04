package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doaju.dto.MensagemDTO;
import br.com.doaju.model.Mensagem;
import br.com.doaju.request.MensagemRequest;

@Component
public class MensagemMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Mensagem requestToModel(MensagemRequest mensagemRequest) {
		return modelMapper.map(mensagemRequest, Mensagem.class);
	}

	public MensagemDTO modelToDTO(Mensagem mensagem) {
		return modelMapper.map(mensagem, MensagemDTO.class);
	}

}
