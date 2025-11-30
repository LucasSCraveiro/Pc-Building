package com.justocraveiro.pcbuilding.repository;

import com.justocraveiro.pcbuilding.model.Peca;
import com.justocraveiro.pcbuilding.model.TipoPeca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {
    List<Peca> findByTipo(TipoPeca tipo);
    Peca findByNome(String nome);
}
