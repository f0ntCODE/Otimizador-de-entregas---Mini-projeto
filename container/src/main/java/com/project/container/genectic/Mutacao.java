package com.project.container.genectic;

import java.util.Random;

public class Mutacao {
    // p: cromossomo (vetor binário), n: tamanho do cromossomo,
    // pesos: vetor de pesos, capacidadeMaxima: capacidade da mochila
    public static int[] trocaSimples(int[] p, int n, int[] pesos, int capacidadeMaxima, double taxaMutacao) {
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            double valorMutacao = random.nextDouble();

            if (valorMutacao < taxaMutacao) {
            System.out.println("Mutação detectada: " + valorMutacao);

                // Inverte o gene (0 -> 1 ou 1 -> 0)
                p[i] = 1 - p[i];

                // Verifica se ultrapassa a capacidade
                if (calcularPeso(p, pesos) > capacidadeMaxima) {
                    // Se ultrapassou, desfaz a mutação
                    p[i] = 1 - p[i];
                }
            }
        }
        return p;
    }

    // Calcula o peso total do cromossomo
    private static int calcularPeso(int[] cromossomo, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < cromossomo.length; i++) {
            if (cromossomo[i] == 1) {
                soma += pesos[i];
            }
        }
        return soma;
    }
}

