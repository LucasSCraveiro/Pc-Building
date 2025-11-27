package com.justocraveiro.pcbuilding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }


    @GetMapping("/pecas")
    public String pecas()
    {
        return "pecas";
    }

    @GetMapping("/comunidade")
    public String comunidade()
    {
        return "comunidade";
    }

    @GetMapping ("/configuracoes")
    public String configuracoes()
    {
        return "configuracoes";
    }

}
