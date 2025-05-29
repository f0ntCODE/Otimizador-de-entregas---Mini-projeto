package com.project.container.controllers;

import com.project.container.model.ObterResultado_Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.container.facade.Mochila;

import java.util.Arrays;

@Controller
public class mainController {


    private Mochila mochila;

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

        mochila = new Mochila(capacidadeMochila, pesoMax, pesoMin, numeroItens);

        int[] dados = mochila.executarMetodoBasico();
        ObterResultado_Model resultadoModel = mochila.getSolucaoInicial();

        //para fins de debug
        System.out.println("CONTROLLER DIZ -> peso: " + dados[1]);
        System.out.println("CONTROLLER DIZ -> lucro: " + dados[0]);
        System.out.println("CONTROLLER DIZ: -> solução inicial" + Arrays.toString(resultadoModel.getSolucaoInicial()));

        model.addAttribute("lucro", dados[0]);
        model.addAttribute("peso", dados[1]);
        model.addAttribute("solucaoInicial", Arrays.toString(resultadoModel.getSolucaoInicial()));
    // Retornar a mesma página do formulário
        return "basic_methods";
    }

    @GetMapping("/subidaEncosta")
    public String executarSubidaEnconsta(@RequestParam("capacidadeMochila") int capacidadeMochila,
                                         @RequestParam("numeroItens") int numeroItens,
                                         Model model){

    }
}
