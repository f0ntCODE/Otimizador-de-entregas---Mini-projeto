package com.project.container.functions;

import java.util.Arrays;

public class SolucaoInicial {
    private int numeroItens;
    private int[] pesos;
    private int[] lucros;
    private int capacidadeMochila;

    public SolucaoInicial(int capacidadeMochila, int[] lucros, int[] pesos, int numeroItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.lucros = lucros;
        this.pesos = pesos;
        this.numeroItens = numeroItens;
    }

    public int[] gerarSolucaoInicial(){
        int[] solucao     = new int[numeroItens];
        int valorPeso     = 0;
        int valorLucro    = 0;
        int maxTentativas = numeroItens * 2;
        int tentativas    = 0;

            while(valorPeso < capacidadeMochila && tentativas < maxTentativas){
                int i = (int) (Math.random() * numeroItens);

                if (solucao[i] == 0 && (valorPeso + pesos[i]) <= capacidadeMochila) {
                    solucao[i] = 1;
                    valorPeso  += pesos[i];
                    valorLucro += lucros[i];
                }
                tentativas ++;
            }
            System.out.println("Solução inicial: " + Arrays.toString(solucao));

        return solucao;
    }

}
