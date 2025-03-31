package com.project.container.functions;

import java.util.Arrays;


public class backPack {

    /*  
     * VARIÁVEIS
     */
    
    private int n = 0;
    private final int maxItens;
    private final int minItens;
    private final int capacidadeMaxima;
    private final int[] p;
    private final int[] s;

    public backPack(int n, int maxItens, int minItens, int capacidadeMaxima){
        this.n = n;
        this.maxItens = maxItens;
        this.minItens = minItens;
        this.capacidadeMaxima = capacidadeMaxima;
        this.p = new int[n];
        this.s = new int[n];

    }

    public int[] gerarProblema() {
        int v[] = new int[n];
        for (int cont = 0; cont < n; cont++) {
            v[cont] = (int) (Math.random() * (maxItens - minItens + 1)) + minItens;
        }
        return v;
    }

    public int[] solucaoInicial(int p[]) {
        int[] s = new int[n];
        int v = 0;
        boolean[] tried = new boolean[n];

        while (true) {
            int i = (int) (Math.random() * n);
            
            if (s[i] == 0 && v + p[i] <= capacidadeMaxima) {
                s[i] = 1;
                v += p[i];
                Arrays.fill(tried, false);
            } else {
                tried[i] = true;
            }

            boolean allTried = true;
            for (boolean b : tried) {
                if (!b) {
                    allTried = false;
                    break;
                }
            }
            if (allTried) break;
        }
        return s;
    }

    public int avaliaSolucao() {
        int v = 0;
        for (int cont = 0; cont < n; cont++) {
            v += s[cont] * p[cont];
        }
        return v;
    }

    

    
    
}
