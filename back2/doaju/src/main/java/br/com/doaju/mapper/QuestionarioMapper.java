package br.com.doaju.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.doaju.dto.QuestionarioDTO;
import br.com.doaju.dto.RankingDTO;
import br.com.doaju.model.Questionario;
import br.com.doaju.model.Ranking;
import br.com.doaju.request.QuestionarioRequest;

@Component
public class QuestionarioMapper {
	@Autowired
    private ModelMapper modelMapper;

    public Questionario requestToModel(QuestionarioRequest questionarioRequest) {
        return modelMapper.map(questionarioRequest, Questionario.class);
    }
    
    public QuestionarioDTO modelToDTO(Questionario questionario) {
        return modelMapper.map(questionario, QuestionarioDTO.class);
    }
    
    public RankingDTO modelToRankDTO(Ranking ranking) {
        return modelMapper.map(ranking, RankingDTO.class);
    }

}

