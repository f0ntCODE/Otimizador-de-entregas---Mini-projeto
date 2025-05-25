package com.project.container.utils;

public abstract class Calculadora {

    public int calcularValor(int[] valores, int[] solucao){
        int soma = 0;

        for(int i = 0; i < valores.length; i ++){
            if(solucao[i] == 1) {

                soma += valores[i];
            }
        }

        return soma;
    }

    public int calcularPeso(int[] pesos, int[] solucao){
        int soma = 0;

        for(int i = 0; i < pesos.length; i ++){
            if(solucao[i] == 1) {

                soma += pesos[i];
            }
        }

        return soma;
    }

}
