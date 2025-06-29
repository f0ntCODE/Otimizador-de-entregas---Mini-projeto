package com.project.container.genectic;

public class Fitness {
    // Calcula o fitness de cada indivíduo para o problema da mochila
    public static int[] avaliarPopulacao(int[][] individuos, int[] pesos, int[] lucros, int capacidadeMochila) {
        int[] fitness = new int[individuos.length];
        int somaPesos = 0;
        int somaLucros = 0;
        for (int i = 0; i < individuos.length; i++) {
            for (int j = 0; j < individuos[i].length; j++) {
                if (individuos[i][j] == 1) {
                    somaPesos += pesos[j];
                    somaLucros += lucros[j];
                }
            }

            // Penalização
            if (somaPesos > capacidadeMochila) {
                fitness[i] = 0;
            } else {
                fitness[i] = somaLucros;
            }
        }

        return fitness;
    }

    // Exibe o fitness de cada indivíduo
    public static void mostrarFitness(int[] fitness) {
        System.out.println("Fitness da população:");
        for (int i = 0; i < fitness.length; i++) {
            System.out.println("Indivíduo " + i + ": " + fitness[i]);
        }
    }
}
