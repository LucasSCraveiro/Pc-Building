package com.justocraveiro.pcbuilding.service.impl;

import com.justocraveiro.pcbuilding.model.Peca;
import com.justocraveiro.pcbuilding.model.TipoPeca;
import com.justocraveiro.pcbuilding.repository.PecaRepository;
import com.justocraveiro.pcbuilding.service.PecaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaServiceImpl implements PecaService {

    private final PecaRepository pecaRepository;

    public PecaServiceImpl(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    @Override
    public List<Peca> findByTipo(TipoPeca tipo) {
        return pecaRepository.findByTipo(tipo);
    }

    @Override
    public Optional<Peca> findById(Long id) {
        return pecaRepository.findById(id);
    }

    @Override
    public Optional<Peca> findByNome(String nome) {
        return Optional.ofNullable(pecaRepository.findByNome(nome));
    }

    @Override
    public Peca save(Peca peca) {
        return pecaRepository.save(peca);
    }
}
