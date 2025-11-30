package com.justocraveiro.pcbuilding.controller;

import com.justocraveiro.pcbuilding.dto.ComputadorFormDto;
import com.justocraveiro.pcbuilding.model.Computador;
import com.justocraveiro.pcbuilding.model.Peca;
import com.justocraveiro.pcbuilding.model.TipoPeca;
import com.justocraveiro.pcbuilding.repository.ComputadorRepository;
import com.justocraveiro.pcbuilding.service.ComputadorService;
import com.justocraveiro.pcbuilding.service.PecaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/computadores")
public class ComputadorController {

    private final ComputadorRepository computadorRepository;
    private final ComputadorService computadorService;
    private final PecaService pecaService;

    public ComputadorController(ComputadorRepository computadorRepository, ComputadorService computadorService, PecaService pecaService) {
        this.computadorRepository = computadorRepository;
        this.computadorService = computadorService;
        this.pecaService = pecaService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "categoria", required = false) String categoria,
                         Model model) {
        List<Computador> modelos;
        if (categoria == null || categoria.trim().isEmpty() || categoria.equalsIgnoreCase("todos")) {
            modelos = computadorRepository.findAll();
        } else {
            modelos = computadorRepository.findByCategoriaIgnoreCase(categoria);
        }
        model.addAttribute("modelos", modelos);
        model.addAttribute("filtroAtual", categoria == null ? "todos" : categoria);
        return "computadores";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("computadorForm", new ComputadorFormDto());
        model.addAttribute("processadores", pecaService.findByTipo(TipoPeca.PROCESSADOR));
        model.addAttribute("rams", pecaService.findByTipo(TipoPeca.RAM));
        model.addAttribute("armazenamentos", pecaService.findByTipo(TipoPeca.ARMAZENAMENTO));
        return "computador-form";
    }

    @PostMapping("/novo")
    public String criar(@Valid @ModelAttribute("computadorForm") ComputadorFormDto computadorForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("processadores", pecaService.findByTipo(TipoPeca.PROCESSADOR));
            model.addAttribute("rams", pecaService.findByTipo(TipoPeca.RAM));
            model.addAttribute("armazenamentos", pecaService.findByTipo(TipoPeca.ARMAZENAMENTO));
            return "computador-form";
        }
        computadorService.createFromDto(computadorForm);
        return "redirect:/computadores";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        Optional<Computador> maybe = computadorRepository.findById(id);
        if (maybe.isEmpty()) {
            return "redirect:/computadores";
        }
        model.addAttribute("computador", maybe.get());
        return "computador-detalhe";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        Optional<Computador> maybe = computadorRepository.findById(id);
        if (maybe.isEmpty()) {
            return "redirect:/computadores";
        }
        Computador c = maybe.get();
        ComputadorFormDto dto = new ComputadorFormDto();
        dto.setNome(c.getNome());
        dto.setDescricao(c.getDescricao());
        dto.setCategoria(c.getCategoria());
        // tentar mapear nomes de peças para ids (se existirem)
        pecaService.findByNome(c.getProcessador()).ifPresent(p -> dto.setProcessadorId(p.getId()));
        pecaService.findByNome(c.getRam()).ifPresent(p -> dto.setRamId(p.getId()));
        pecaService.findByNome(c.getArmazenamento()).ifPresent(p -> dto.setArmazenamentoId(p.getId()));
        model.addAttribute("computadorForm", dto);
        model.addAttribute("processadores", pecaService.findByTipo(TipoPeca.PROCESSADOR));
        model.addAttribute("rams", pecaService.findByTipo(TipoPeca.RAM));
        model.addAttribute("armazenamentos", pecaService.findByTipo(TipoPeca.ARMAZENAMENTO));
        model.addAttribute("editingId", id);
        return "computador-form";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute("computadorForm") ComputadorFormDto computadorForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("processadores", pecaService.findByTipo(TipoPeca.PROCESSADOR));
            model.addAttribute("rams", pecaService.findByTipo(TipoPeca.RAM));
            model.addAttribute("armazenamentos", pecaService.findByTipo(TipoPeca.ARMAZENAMENTO));
            model.addAttribute("editingId", id);
            return "computador-form";
        }
        computadorService.updateFromDto(id, computadorForm);
        return "redirect:/computadores";
    }

}
