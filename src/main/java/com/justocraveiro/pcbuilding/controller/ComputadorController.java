package com.justocraveiro.pcbuilding.controller;
import com.justocraveiro.pcbuilding.model.Computador;
import com.justocraveiro.pcbuilding.repository.ComputadorRepository;
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

    public ComputadorController(ComputadorRepository computadorRepository) {
        this.computadorRepository = computadorRepository;
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
        model.addAttribute("computador", new Computador());
        return "computador-form";
    }

    @PostMapping("/novo")
    public String criar(@Valid @ModelAttribute("computador") Computador computador, BindingResult result) {
        if (result.hasErrors()) {
            return "computador-form";
        }
        computadorRepository.save(computador);
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
        model.addAttribute("computador", maybe.get());
        return "computador-form";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute("computador") Computador computador, BindingResult result) {
        if (result.hasErrors()) {
            return "computador-form";
        }
        computador.setId(id);
        computadorRepository.save(computador);
        return "redirect:/computadores";
    }

}
