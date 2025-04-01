package com.project.container.functions;

import java.util.Arrays;


public class backPack {

    /*  
     * VARIÁVEIS
     */
    
    private int maxItens = 0;
    private final int pesoMax;
    private final int pesoMin;
    private final int capacidadeCarga;
    private final int[] p;
    private final int[] s;

    public backPack(int maxItens, int pesoMax, int pesoMin, int capacidadeCarga){
        this.maxItens = maxItens;
        this.pesoMax = pesoMax;
        this.pesoMin = pesoMin;
        this.capacidadeCarga = capacidadeCarga;
        this.p = new int[maxItens];
        this.s = new int[maxItens];

    }

    public int[] gerarProblema() {
        int v[] = new int[maxItens];
        for (int cont = 0; cont < maxItens; cont++) {
            v[cont] = (int) (Math.random() * (maxItens - pesoMin + 1)) + pesoMin;
        }
        return v;
    }

    public int[] solucaoInicial(int l[], int p[]) { //l = lucro; p = peso
        int[] s = new int[maxItens];
        int v = 0;
        boolean[] tried = new boolean[maxItens];

        while (true) {
            int i = (int) (Math.random() * maxItens);
            
            if (s[i] == 0 && v + p[i] <= capacidadeCarga) {
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
        for (int cont = 0; cont < maxItens; cont++) {
            v += s[cont] * p[cont];
        }
        return v;
    }

    

    
    
}
