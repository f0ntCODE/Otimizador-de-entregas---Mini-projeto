package com.project.container.genectic;

import java.util.Random;

public class Selecao {
    // Seleção por torneio: retorna o índice do indivíduo vencedor
    public static int torneio(int[] fitness, int tamanhoPopulacao) {
        Random random = new Random();
        int i1 = random.nextInt(tamanhoPopulacao);
        int i2 = random.nextInt(tamanhoPopulacao);

        if (fitness[i1] > fitness[i2]) {
            return i1;
        } else {
            return i2;
        }
    }
}
