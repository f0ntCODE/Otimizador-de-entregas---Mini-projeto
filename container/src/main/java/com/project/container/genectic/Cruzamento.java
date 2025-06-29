package com.project.container.genectic;

public class Cruzamento {
    // Cruzamento de um ponto: recebe dois pais, ponto de corte e tamanho do cromossomo
    // Retorna um array com dois filhos
    public static int[][] cruzamento(int[] p1, int[] p2, int corte, int n) {
        int[] d1 = new int[n];
        int[] d2 = new int[n];

        // Copia os genes até o ponto de corte
        for (int i = 0; i < corte; i++) {
            d1[i] = p1[i];
            d2[i] = p2[i];
        }
        // Copia os genes após o ponto de corte
        for (int i = corte; i < n; i++) {
            d1[i] = p2[i];
            d2[i] = p1[i];
        }
        return new int[][]{d1, d2};
    }
}
