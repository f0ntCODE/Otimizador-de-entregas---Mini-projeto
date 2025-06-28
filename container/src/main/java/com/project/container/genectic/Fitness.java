package com.project.container.genectic;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;

import static com.project.container.genectic.descendent.Descendent.converterParaMatriz;

public class Fitness {
    private ObterResultado_Model resultado;
    private Avaliador avaliador;

    public Fitness(ObterResultado_Model resultado){
            this.resultado = resultado;
        }

    public void calcularFitness(ObterResultado_Model resultado) {
        int tamanhoPopulacao = resultado.getTamanhoPopulacao();
        int tamanhoProblema = resultado.getTamanhoProblema();
        int[][] populacao = converterParaMatriz(resultado.getPopulacao(), tamanhoPopulacao, tamanhoProblema);
        int[] vetorPesos = resultado.getPesos();
        int capacidadeMaxima = resultado.getPesoMaximo();

        if (populacao == null || vetorPesos == null)
            throw new IllegalStateException("População ou vetor de pesos não inicializado!");

        double[] aptidao = new double[tamanhoPopulacao];
        double soma = 0.0;

        for (int i = 0; i < tamanhoPopulacao; i++) {
            int[] individuo = populacao[i];

            int peso = calcularValorIndividuo(individuo, vetorPesos);
            int valor = calcularValorIndividuo(individuo, resultado.getValores()); // calcule o valor/lucro do indivíduo (use vetor de valores se for o caso)

            if (peso > capacidadeMaxima) {
                aptidao[i] = 0;
            } else {
                aptidao[i] = valor;
            }
            soma += aptidao[i];
        }

        for (int i = 0; i < tamanhoPopulacao; i++) {
            aptidao[i] = soma != 0 ? aptidao[i] / soma : 0;
        }

        resultado.setAptidao(aptidao);
    }

    /**
     * Calcula o valor/lucro total de um indivíduo (solução) para o problema da mochila.
     * @param individuo Vetor binário representando os itens selecionados (1 = selecionado, 0 = não selecionado)
     * @param vetorValores Vetor de valores/lucros de cada item
     * @return Soma dos valores dos itens selecionados
     */
    public static int calcularValorIndividuo(int[] individuo, int[] vetorValores) {
        int valorTotal = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i] == 1) {
                valorTotal += vetorValores[i];
            }
        }
        return valorTotal;
    }

}
