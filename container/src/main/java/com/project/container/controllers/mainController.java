package com.project.container.controllers;

import com.project.container.model.ObterResultado_Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.container.facade.Mochila;

@Controller
public class mainController {
    private Mochila mochila;
    private ObterResultado_Model resultadoModel;

    //************************************Redirecionamento de páginas

    //iniciar página inicial
    @GetMapping("/")
    public String mainPage() {
     
        return "index";
    }

    @GetMapping("/metodos")
    public String metodosPage() {



        return "basic_methods";
    }

    @GetMapping("/info")
    public String infoPage() {

        return "info";
    }

    /* AÇÃO */

    @PostMapping("/gerar_problema")
        public String gerarProblema(@RequestParam("capacidadeMochila") int capacidadeMochila,
                            @RequestParam("numeroItens") int numeroItens,
                            Model model) {

    // Obter os resultados da classe Mochila
        final int pesoMax = 550;    //final = valores constantes
        final int pesoMin = 50;

        new Mochila(capacidadeMochila, pesoMax, pesoMin, numeroItens);

        mochila.executarMetodoBasico();

        model.addAttribute("lucro", resultadoModel.getSomaLucro());
        model.addAttribute("peso", resultadoModel.getSomaPeso());
    // Retornar a mesma página do formulário
        return "basic_methods";
}



    
    
    
}
