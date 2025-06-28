package com.project.container.genectic.mutation;

import com.project.container.model.ObterResultado_Model;

import java.util.Random;

/**
 * Implementa mutação de troca simples para o algoritmo genético da mochila.
 */
public class Mutation {
    private ObterResultado_Model resultado;
    private final Random rand = new Random();

    public Mutation(ObterResultado_Model resultado){
        this.resultado = resultado;
    }

    /**
     * Realiza mutação de troca simples em um indivíduo.
     * Desliga um gene 1 aleatório e tenta ligar genes à direita, respeitando o peso máximo.
     */
    public void mutacao(int[] individuo, ObterResultado_Model model) {
        int n = individuo.length;
        int countOnes = 0;
        for (int bit : individuo) if (bit == 1) countOnes++;
        if (countOnes == 0) return; // Nenhum gene 1 para desligar
        int i;

        if (individuo == null || individuo.length == 0) {
            throw new IllegalArgumentException("Indivíduo não pode ser nulo ou vazio!");
        }
        if (model == null) {
            throw new IllegalArgumentException("Model não pode ser nulo!");
        }
        int[] pesos = model.getPesos();
        if (pesos == null || pesos.length != individuo.length) {
            throw new IllegalStateException("Vetor de pesos não inicializado ou tamanho inconsistente!");
        }
        Integer pesoMaximo = model.getPesoMaximo();
        if (pesoMaximo == null) {
            throw new IllegalStateException("Peso máximo não inicializado!");
        }

        do {
            i = rand.nextInt(n);
        } while (individuo[i] == 0);

        individuo[i] = 0;
        pesos = model.getPesos();
        pesoMaximo = model.getPesoMaximo();

        for (int j = i + 1; j < n; j++) {
            individuo[j] = 1;
            if (calculaPeso(individuo, pesos) > pesoMaximo) {
                individuo[j] = 0;
            }
        }
    }

    /**
     * Calcula o peso total de um indivíduo.
     */
    public static int calculaPeso(int[] individuo, int[] pesos) {
        if (individuo == null || pesos == null || individuo.length != pesos.length) {
            throw new IllegalArgumentException("Indivíduo e pesos devem ser não nulos e de mesmo tamanho!");
        }
        int pesoTotal = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i] == 1) {
                pesoTotal += pesos[i];
            }
        }
        return pesoTotal;
    }

}
