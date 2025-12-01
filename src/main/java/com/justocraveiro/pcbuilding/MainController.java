package com.justocraveiro.pcbuilding;

import com.justocraveiro.pcbuilding.model.TipoPeca;
import com.justocraveiro.pcbuilding.service.PecaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final PecaService pecaService;

    public MainController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/pecas")
    public String pecas(Model model) {
        model.addAttribute("processadores", pecaService.findByTipo(TipoPeca.PROCESSADOR));
        model.addAttribute("rams", pecaService.findByTipo(TipoPeca.RAM));
        model.addAttribute("armazenamentos", pecaService.findByTipo(TipoPeca.ARMAZENAMENTO));
        model.addAttribute("gpus", pecaService.findByTipo(TipoPeca.GPU));
        model.addAttribute("placasMae", pecaService.findByTipo(TipoPeca.PLACA_MAE));
        model.addAttribute("fontes", pecaService.findByTipo(TipoPeca.FONTE));
        return "pecas";
    }

    @GetMapping("/comunidade")
    public String comunidade() {
        return "comunidade";
    }

    @GetMapping ("/configuracoes")
    public String configuracoes() {
        return "configuracoes";
    }

}
