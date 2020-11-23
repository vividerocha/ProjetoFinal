package br.com.doaju.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
//import br.com.doaju.dto.EquipamentosTecnicoRegiaoDTO;
import br.com.doaju.dto.HistoricoEquipamentoDTO;
import br.com.doaju.exception.EntidadeNaoEncontradaException;
import br.com.doaju.mapper.HistoricoEquipamentoMapper;
import br.com.doaju.model.Equipamento;
import br.com.doaju.model.HistoricoEquipamento;
import br.com.doaju.model.Usuario;
import br.com.doaju.repository.EquipamentoRepository;
import br.com.doaju.repository.HistoricoEquipamentoRepository;
import br.com.doaju.request.HistoricoEquipamentoRequest;

@Service
public class HistoricoEquipamentoService {
	@Autowired
	private HistoricoEquipamentoRepository repository;
	
	@Autowired
	private EquipamentoRepository repositoryEquip;
	
	
	@Autowired
	private HistoricoEquipamentoMapper mapper;
	
	@Autowired
	private UsuarioService userService;

	@Transactional
	public HistoricoEquipamentoDTO salvar(HistoricoEquipamentoRequest historicoEquipamentoRequest) {
		
		System.out.print(historicoEquipamentoRequest.getIdUsuario());
		Usuario usuario = userService.buscarPorId(historicoEquipamentoRequest.getIdUsuario()).get();
		HistoricoEquipamento historicoEquipamento = mapper.requestToModel(historicoEquipamentoRequest);
		System.out.print(usuario);
		historicoEquipamento.setUsuario(usuario);
	    return mapper.modelToDTO( repository.save(historicoEquipamento) );		
	}
	
	public List<HistoricoEquipamento> buscar(Equipamento equipamento) {
		return repository.findByEquipamento(equipamento.getId());
	}

	@Transactional
	public void excluir(Long id) {
		
		try {
			repository.deleteById(id);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Historico Equipamento não encontrado!") {
				private static final long serialVersionUID = 1L;
			};
		}			
	}
	
	public List<HistoricoEquipamentoDTO> listar() {
		
		return repository.findAll()
				.stream()
				.map(hist -> mapper.modelToDTO(hist))
				.collect(Collectors.toList());	
	}
	
	public List<HistoricoEquipamentoDTO> buscarHistorico(Long idEquipamento) {
		
		Equipamento equipamento = repositoryEquip.findById(idEquipamento).get();
		return repository.findByEquipamento(equipamento.getId())
				.stream()
				.map(hist -> mapper.modelToDTO(hist))
				.collect(Collectors.toList());	
	}
	
	
	public List<EquipamentosTecnicoRegiaoDTO> buscaEquipamentosParaReparoPorRegiao(String regiao) {
	//consulta os equipamentos disponíveis para retirada do técnico	
		return repository.buscaEquipamentosParaReparoPorRegiao(regiao)
				.stream()
				.map(hist -> mapper.modelToEqDTO(hist))
				.collect(Collectors.toList());	
	}
	
	public List<EquipamentosTecnicoRegiaoDTO> buscaEquipamentosParaDistribuicaoPorRegiao(String regiao) {
		//consulta os equipamentos disponíveis para Distribuição
		List<Long> equipamentosDistribuidos = repository.buscaEquipamentosDistribuidos();
		if(equipamentosDistribuidos.size() == 0) {
			equipamentosDistribuidos.add((long) 0);
		}
		return repository.buscaEquipamentosParaDistribuicaoPorRegiao(regiao, equipamentosDistribuidos)
				.stream()
				.map(hist -> mapper.modelToEqDTO(hist))
				.collect(Collectors.toList());	
	}
	
	public EquipamentosTecnicoRegiaoDTO buscaEquipamentoDistribuido(Long id){
		EquipamentosTecnicoRegiaoDTO equip = mapper.modelToEqDTO(repository.buscaEquipamentoDistribuido(id));
		return equip;
	}
	
	public EquipamentosTecnicoRegiaoDTO buscaTecnico(Long id){
		EquipamentosTecnicoRegiaoDTO equip = mapper.modelToEqDTO(repository.buscaTecnico(id));
		return equip;
	}
	
	public Long buscaUltimoHistorico(Long id) {
		return repository.buscaUltimaSituacao(id);
	}

}
