package br.com.digitalhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.model.Tecnico;
import br.com.digitalhouse.repository.TecnicoRepository;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    @Autowired
    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico salvar(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }
}