package com.project.container.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.container.functions.Mochila;

@Controller
public class mainController {

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
        String[] resultados = Mochila.obterResultados(capacidadeMochila, 550, 50, numeroItens);

    // Adicionar os resultados ao modelo
        model.addAttribute("resultadoPesos", resultados[0]);
        model.addAttribute("resultadoLucros", resultados[1]);
        model.addAttribute("resultadoAvaliado", resultados[2]);

    // Retornar a mesma página do formulário
        return "basic_methods";
}



    
    
    
}
