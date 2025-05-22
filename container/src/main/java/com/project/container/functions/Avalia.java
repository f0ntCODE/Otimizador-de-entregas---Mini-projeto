package com.project.container.functions;

import java.util.Arrays;

//@SuppressWarnings("unused")
public class Avalia {

    private int[] solucao;
    private int[] pesos;
    private int[] lucros;
    private int numeroItens;

    

    public Avalia(int[] solucao, int[] pesos, int[] lucros, int numeroItens) {
        this.solucao = solucao;
        this.pesos = pesos;
        this.lucros = lucros;
        this.numeroItens = numeroItens;
    }

    public String avaliaSolucao(int[] solucao, int[] p, int[] l, int numeroItens){
        int valorPeso  = 0;
        int valorLucro = 0;

        for(int cont = 0; cont < numeroItens; cont ++){

            valorPeso  += solucao[cont] * p[cont];
            valorLucro += solucao[cont] * l[cont];
        }

        System.out.println("Peso máximo: " + valorPeso + "\n Lucro máximo: " + valorLucro);

        return "Solução inicial: " + Arrays.toString(solucao) + " \n Lucro máximo: " + valorLucro + " \n Peso máximo: " + valorPeso;
    }

}
