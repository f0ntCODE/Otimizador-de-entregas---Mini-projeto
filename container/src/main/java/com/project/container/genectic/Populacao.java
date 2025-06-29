package com.project.container.genectic;

import java.util.Random;

public class Populacao {
    private int[][] individuos;
    private int tamanhoPopulacao;
    private int tamanhoCromossomo;

    public Populacao(int tamanhoPopulacao, int tamanhoCromossomo) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.tamanhoCromossomo = tamanhoCromossomo;
        this.individuos = new int[tamanhoPopulacao][tamanhoCromossomo];
    }

    // Gera população inicial aleatória (0 ou 1)
    public void gerarPopulacaoInicial() {
        Random random = new Random();
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = 0; j < tamanhoCromossomo; j++) {
                individuos[i][j] = random.nextInt(2); // 0 ou 1
            }
        }
    }

    // Exibe a população no terminal
    public void mostrarPopulacao() {
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = 0; j < tamanhoCromossomo; j++) {
                System.out.print(individuos[i][j]);
            }
            System.out.println();
        }
    }

    // Getter para acesso externo
    public int[][] getIndividuos() {
        return individuos;
    }
}
