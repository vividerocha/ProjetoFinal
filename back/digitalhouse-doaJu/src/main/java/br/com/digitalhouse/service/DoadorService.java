package br.com.digitalhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.model.Doador;
import br.com.digitalhouse.repository.DoadorRepository;

@Service
public class DoadorService {

    private final DoadorRepository doadorRepository;

    @Autowired
    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }

    public Doador salvar(Doador doador) {
        return doadorRepository.save(doador);
    }
}