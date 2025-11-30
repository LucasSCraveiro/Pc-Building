package com.justocraveiro.pcbuilding.service.impl;

import com.justocraveiro.pcbuilding.dto.ComputadorFormDto;
import com.justocraveiro.pcbuilding.model.Computador;
import com.justocraveiro.pcbuilding.model.Peca;
import com.justocraveiro.pcbuilding.repository.ComputadorRepository;
import com.justocraveiro.pcbuilding.service.ComputadorService;
import com.justocraveiro.pcbuilding.service.PecaService;
import com.justocraveiro.pcbuilding.util.PrecoCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComputadorServiceImpl implements ComputadorService {

    private final ComputadorRepository computadorRepository;
    private final PecaService pecaService;

    public ComputadorServiceImpl(ComputadorRepository computadorRepository, PecaService pecaService) {
        this.computadorRepository = computadorRepository;
        this.pecaService = pecaService;
    }

    @Override
    public Computador createFromDto(ComputadorFormDto dto) {
        Computador c = new Computador();
        c.setNome(dto.getNome());
        c.setDescricao(dto.getDescricao());
        c.setCategoria(dto.getCategoria());

        List<Peca> pecas = new ArrayList<>();

        Optional<Peca> proc = pecaService.findById(dto.getProcessadorId());
        proc.ifPresent(p -> {
            c.setProcessador(p.getNome());
            pecas.add(p);
        });

        Optional<Peca> ram = pecaService.findById(dto.getRamId());
        ram.ifPresent(p -> {
            c.setRam(p.getNome());
            pecas.add(p);
        });

        Optional<Peca> ar = pecaService.findById(dto.getArmazenamentoId());
        ar.ifPresent(p -> {
            c.setArmazenamento(p.getNome());
            pecas.add(p);
        });

        BigDecimal total = PrecoCalculator.soma(pecas);
        c.setPreco(total);
        return computadorRepository.save(c);
    }

    @Override
    public Computador updateFromDto(Long id, ComputadorFormDto dto) {
        Optional<Computador> maybe = computadorRepository.findById(id);
        if (maybe.isEmpty()) {
            throw new IllegalArgumentException("Computador não encontrado: " + id);
        }
        Computador c = maybe.get();
        c.setNome(dto.getNome());
        c.setDescricao(dto.getDescricao());
        c.setCategoria(dto.getCategoria());

        List<Peca> pecas = new ArrayList<>();

        pecaService.findById(dto.getProcessadorId()).ifPresent(p -> {
            c.setProcessador(p.getNome());
            pecas.add(p);
        });
        pecaService.findById(dto.getRamId()).ifPresent(p -> {
            c.setRam(p.getNome());
            pecas.add(p);
        });
        pecaService.findById(dto.getArmazenamentoId()).ifPresent(p -> {
            c.setArmazenamento(p.getNome());
            pecas.add(p);
        });

        BigDecimal total = PrecoCalculator.soma(pecas);
        c.setPreco(total);
        return computadorRepository.save(c);
    }
}
