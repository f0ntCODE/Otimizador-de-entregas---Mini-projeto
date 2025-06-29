package com.project.container.controllers;

import com.project.container.genectic.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/genetic")
@Controller
public class genecticController {

    private com.project.container.genectic.utils.Gerador gerador;
    private Cruzamento cruzamento;
    private Descendentes descendentes;
    private Mutacao mutacao;
    private Fitness fitness;
    private Populacao populacao;
    private Selecao selecao;

    @GetMapping("/genetico")
    public String openGenecticPage(){

        return "genetic_algorithm";
    }

    @PostMapping("/executar_algoritmo")
    public String executarAlgoritmo(
            @RequestParam("tamanhoProblema") int tamanhoProblema,
            @RequestParam("tp") int tamanhoPopulacao,
            @RequestParam("tc") double taxaCruzamento,
            @RequestParam("tm") double taxaMutacao,
            @RequestParam("ng") int numGeracoes,
            @RequestParam("li") int limiteMochila,
            Model model) {

        // Gera pesos e lucros aleatórios
        int[] pesos = gerador.gerarPesos(tamanhoProblema, 2, 25);
        int[] lucros = gerador.gerarLucros(tamanhoProblema, 3, 550);

        // Define capacidade da mochila (exemplo: 50% da soma dos pesos)
        int capacidadeMochila = (int) (0.5 * soma(pesos));

        // Executa o algoritmo genético
        String resultado = executarAG(tamanhoPopulacao, tamanhoProblema, numGeracoes,
                taxaCruzamento, taxaMutacao, pesos, lucros, limiteMochila);

        model.addAttribute("resultado", resultado);
        return "genetic_algorithm";
    }

    private int soma(int[] vetor) {
        int total = 0;
        for (int v : vetor) total += v;
        return total;
    }

    private String executarAG(int tamanhoPopulacao, int tamanhoCromossomo, int numGeracoes,
                              double taxaCruzamento, double taxaMutacao, int[] pesos, int[] lucros,
                              int capacidadeMochila) {

        // Implementação simplificada do AG, similar ao que já discutimos
        Populacao populacao = new Populacao(tamanhoPopulacao, tamanhoCromossomo);
        populacao.gerarPopulacaoInicial();
        int[][] individuos = populacao.getIndividuos();

        int melhorFitnessGlobal = Integer.MIN_VALUE;
        int[] melhorIndividuoGlobal = null;

        StringBuilder log = new StringBuilder();

        for (int geracao = 0; geracao < numGeracoes; geracao++) {
            int[] fitness = Fitness.avaliarPopulacao(individuos, pesos, lucros, capacidadeMochila);

            int melhorFitness = Integer.MIN_VALUE;
            int indiceMelhor = -1;
            for (int i = 0; i < fitness.length; i++) {
                if (fitness[i] > melhorFitness) {
                    melhorFitness = fitness[i];
                    indiceMelhor = i;
                }
            }

            if (melhorFitness > melhorFitnessGlobal) {
                melhorFitnessGlobal = melhorFitness;
                melhorIndividuoGlobal = individuos[indiceMelhor].clone();
            }

            int pesoAtual = 0;
            for (int j = 0; j < melhorIndividuoGlobal.length; j++) {
                if (melhorIndividuoGlobal[j] == 1) {
                    pesoAtual += pesos[j];
                }
            }

            log.append(String.format("Geração %d - Melhor fitness: %d | Peso atual: %d | TC: %.2f | TM: %.2f\n",
                    geracao, melhorFitness, pesoAtual, taxaCruzamento, taxaMutacao));

            int[] fitnessAtual = Fitness.avaliarPopulacao(individuos, pesos, lucros, capacidadeMochila);
            individuos = Descendentes.gerarDescendentes(individuos, fitnessAtual, tamanhoPopulacao, tamanhoCromossomo,
                    taxaCruzamento, taxaMutacao, pesos, capacidadeMochila);
        }

        log.append("\nMelhor solução encontrada:\nIndivíduo: ");
        for (int gene : melhorIndividuoGlobal) {
            log.append(gene);
        }
        log.append("\nFitness: ").append(melhorFitnessGlobal);
        log.append("\nPeso: ").append(calcularPeso(melhorIndividuoGlobal, pesos));
        return log.toString();
    }

    private int calcularPeso(int[] individuo, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i] == 1) soma += pesos[i];
        }
        return soma;
    }

}
