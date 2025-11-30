package com.justocraveiro.pcbuilding.service;

import com.justocraveiro.pcbuilding.model.Peca;
import com.justocraveiro.pcbuilding.model.TipoPeca;

import java.util.List;
import java.util.Optional;

public interface PecaService {
    List<Peca> findByTipo(TipoPeca tipo);
    Optional<Peca> findById(Long id);
    Optional<Peca> findByNome(String nome);
    Peca save(Peca peca);
}
