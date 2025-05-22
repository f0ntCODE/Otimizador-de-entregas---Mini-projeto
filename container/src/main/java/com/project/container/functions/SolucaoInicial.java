package com.project.container.functions;

import java.util.Arrays;

public class SolucaoInicial {
    private int numeroItens;
    private int[] pesos;
    private int[] lucros;
    private int capacidadeMochila;

    public SolucaoInicial(int numeroItens, int[] pesos, int[] lucros, int capacidadeMochila) {
        this.numeroItens = numeroItens;
        this.pesos = pesos;
        this.lucros = lucros;
        this.capacidadeMochila = capacidadeMochila;
    }

    public int[] gerarSolucaoInicial(int numeroItens, int[] p, int[] l, int capacidadeMochila){
        int[] solucao     = new int[numeroItens];
        int valorPeso     = 0;
        int valorLucro    = 0;
        int maxTentativas = numeroItens * 2;
        int tentativas    = 0;

            while(valorPeso < capacidadeMochila && tentativas < maxTentativas){
                int i = (int) (Math.random() * numeroItens);

                if (solucao[i] == 0 && (valorPeso + p[i]) <= capacidadeMochila) {
                    solucao[i] = 1;
                    valorPeso  += p[i];
                    valorLucro += l[i];
                }
                tentativas ++;
            }
            System.out.println("Solução inicial: " + Arrays.toString(solucao));

        return solucao;
    }

}
