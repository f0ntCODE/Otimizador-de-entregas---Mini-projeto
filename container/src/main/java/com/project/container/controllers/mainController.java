package com.project.container.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.container.functions.backPack;

@Controller
public class mainController {

    //************************************Redirecionamento de páginas

    @GetMapping("/")
    public String mainPage() {
        
        
        return "index";
    }

    @GetMapping("/metodos")
    public String metodosPage() {
        backPack mochila = new backPack(6, 10, 0, 15    );
        
        int[] vetor = mochila.gerarProblema();

        System.out.println(Arrays.toString(vetor) + " Problema gerado"); //para fins de apuração
        //BUG! int[] solucao = mochila.solucaoInicial(vetor);

        //BUG! System.out.println(Arrays.toString(solucao) + " Solução inicial");

        int avaliado = mochila.avaliaSolucao();
        System.out.println(avaliado + " Avaliação");


        return "basic_methods";
    }

    @GetMapping("/info")
    public String infoPage() {

        return "info";
    }


    
    
    
}
