package com.project.container.controllers;

import com.project.container.facade.Mochila;
import com.project.container.genectic.Fitness;
import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;
import com.project.container.utils.Gerador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@RequestMapping("/genetic")
@Controller
public class genecticController {

    private Mochila mochila;
    private Avaliador avaliador;
    private Fitness fitness;

    @GetMapping("/genetico")
    public String openGenecticPage(){

        return "genetic_algorithm";
    }

    @PostMapping("/executar_algoritmo")
    public String executarMetodoGenetico(
            @RequestParam int tamanhoProblema,
            @RequestParam int tp,
            @RequestParam double tc,
            @RequestParam double tm,
            @RequestParam int ng,
            @RequestParam int ig,
            Model model
    ) {
        ObterResultado_Model modelo = new ObterResultado_Model();

// Use o Gerador para preencher automaticamente os campos necessários
        Gerador gerador = new Gerador(600, tamanhoProblema, modelo);
        Avaliador avaliador = new Avaliador(modelo);

        avaliador.avaliarIndividuos(modelo); // Calcula lucros/pesos dos indivíduos
        fitness.calcularFitness(modelo);     // Calcula e seta o vetor de aptidão (fitness)
        avaliador.avaliarIndividuos(modelo);


        // Instancie o Facade com os parâmetros necessários
        Mochila mochila = new Mochila(ig, tp, ng, tc, tm, tamanhoProblema, modelo);

        // Execute o algoritmo genético
        int[] resultado = mochila.executarAlgoritmoGenetico(
                tc, tm, tamanhoProblema, tp, ng, 1);

        // Adicione o resultado ao Model para exibir na view
        model.addAttribute("resultado", Arrays.toString(resultado));
        return "genetic_algorithm";
    }


}
