package com.project.container.genectic.utils;

import java.util.Random;

public class Gerador {

    // Gera um vetor de pesos aleatórios no intervalo [min, max]
    public static int[] gerarPesos(int tamanho, int min, int max) {
        int[] pesos = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            pesos[i] = random.nextInt(max - min + 1) + min;
        }
        return pesos;
    }

    // Gera um vetor de lucros (valores) aleatórios no intervalo [min, max]
    public static int[] gerarLucros(int tamanho, int min, int max) {
        int[] lucros = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            lucros[i] = random.nextInt(max - min + 1) + min;
        }
        return lucros;
    }
}
