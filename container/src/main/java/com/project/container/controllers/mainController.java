package com.project.container.controllers;

import com.project.container.functions.SubidaEncosta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.container.functions.Mochila;
import com.project.container.functions.MochilaBasica;

import java.util.Arrays;

@Controller
public class mainController {

    @Autowired
    private Mochila mochila;
    private SubidaEncosta encosta;

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

        String[] res = resultados(capacidadeMochila, numeroItens, pesoMin, pesoMax);

    // Adicionar os resultados ao modelo
        model.addAttribute("resultadoPesos", res[0]);
        model.addAttribute("resultadoLucros", res[1]);
        model.addAttribute("resultadoAvaliado", res[2]);

    // Retornar a mesma página do formulário
        return "basic_methods";
}

//em fase de implementação
    @PostMapping("/subidaEncosta")
    public String subidaEncosta(Model model, @RequestParam("") int capacidade,
                                @RequestParam("capacidadeMochila") int capacidadeMochila,
                                @RequestParam("numeroItens") int numeroItens){

        final int pesoMax = 550;    //final = valores constantes
        final int pesoMin = 50;

        new Mochila(capacidadeMochila, pesoMax, pesoMin, numeroItens);

        int valorTotal = resultados[1];

        int[] solucaoInicial = resultados[2];


        int[] valorEncosta = encosta.subidaEncosta(solucaoInicial, valorTotal);

        return "";
    }

    @PostMapping("/subidaEncostaTentativa")
    public String subidaComTentativa(Model model, @RequestParam("") int capacidade,
                                     @RequestParam("") int tMax){

        return "";
    }

    public String[] resultados(int capacidadeMochila, int pesoMax, int pesoMin, int numeroItens){

        new Mochila(capacidadeMochila, pesoMax, pesoMin, numeroItens);

        mochila.

        return resultado;
    }
    
}
