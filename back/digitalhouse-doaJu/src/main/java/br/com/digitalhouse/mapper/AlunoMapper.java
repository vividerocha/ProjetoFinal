package br.com.digitalhouse.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalhouse.dto.AlunoDTO;
import br.com.digitalhouse.model.Aluno;
import br.com.digitalhouse.request.AlunoRequest;

@Component
public class AlunoMapper {
	@Autowired
    private ModelMapper modelMapper;

    public Aluno requestToModel(AlunoRequest alunoRequest) {
        return modelMapper.map(alunoRequest, Aluno.class);
    }
    
    public AlunoDTO modelToDTO(Aluno aluno) {
        return modelMapper.map(aluno, AlunoDTO.class);
    }

}
