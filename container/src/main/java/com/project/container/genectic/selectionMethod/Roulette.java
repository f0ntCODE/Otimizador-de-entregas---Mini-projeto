package com.project.container.genectic.selectionMethod;

import com.project.container.model.ObterResultado_Model;

import java.util.Random;

public class Roulette {
    private ObterResultado_Model resultado;
    private static  final Random rand = new Random();

    public Roulette(ObterResultado_Model resultado){
        this.resultado = resultado;

    }

    public int selecionarCandidato() {
        double[] aptidao = resultado.getAptidao();
        int tamanhoPopulacao = resultado.getTamanhoPopulacao();

        if (aptidao == null || aptidao.length == 0)
            throw new IllegalStateException("Vetor de aptidão vazio!");

        double aleatorio = rand.nextDouble(); // entre 0 e 1
        double soma = aptidao[0];
        int i = 1;

        while ((soma < aleatorio) && (i < tamanhoPopulacao)) {
            soma += aptidao[i];
            i++;
        }
        return i - 1;
    }

}
