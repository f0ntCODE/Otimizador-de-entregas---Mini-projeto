package com.project.container.functions;

import java.util.Arrays;

public abstract class Sucessores {

    private Avalia avalia;
    private SolucaoInicial solucaoInicial;

    //preciso importar os valores de Avalia e de Solução inicial
    public int[] gerarSucessores(int[] mochilaAvaliada, int[] vetorValores, int[] vetorPesos, int pesoMax,
                                 int valorMax){
        int[] vetorAtual = mochilaAvaliada;
        int[] arrayValor     = vetorValores;
        int[] arrayPeso     = vetorPesos;

        int tamanho      = vetorAtual.length;

        int[] melhorVetor     = Arrays.copyOf(vetorAtual, tamanho); //cópia do vetor
        int[] referencia = Arrays.copyOf(mochilaAvaliada, tamanho);
        int[] pesos      = arrayPeso;
        int[] valores    = arrayValor;

        int pMax = 1;//preciso pegar o peso máximo
        int vMax = 1;//preciso pegar do valor máximo

        int vAtual = 1; //pegar do avalia
        int pAtual = 1;//pegar do avalia

        for(int i = 0; i < tamanho; i ++){
            if(vetorAtual[i] == 1){ //se o valor no vetor atual estiver na mochila
                vetorAtual[i] = 1 - vetorAtual[i];
                pMax -= pesos[i];
                vMax -= valores[i];



                for (int j = 0; j < tamanho; j ++){
                    if(vetorAtual[j] == vetorAtual[i] || vetorAtual[j] == 0){
                        continue;
                    }
                    else{

                        if((pesos[j] + pAtual) >= pesoMax){

                        }
                    }
                }
            }
        }
    }

}
