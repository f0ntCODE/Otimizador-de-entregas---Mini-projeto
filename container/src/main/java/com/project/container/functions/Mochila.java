package com.project.container.functions;

public class Mochila {

    /*  
     * VARIÁVEIS
     */
    private int capacidadeMochila, pMax, pMin, numeroItens = 0;

     public Mochila(int capacidadeMochila, int pMax, int pMin, int numeroItens){
        this.capacidadeMochila = capacidadeMochila;
        this.pMax = pMax;
        this.pMin = pMin;
        this.numeroItens = numeroItens;
     }
    
     public static int[] gerarPeso(int capacidadeMochila, int pMax, int pMin){

        int[] peso = new int[capacidadeMochila];

        for (int cont = 0; cont < capacidadeMochila; cont ++) {
            peso[cont] = (int) (Math.random() * (pMax - pMin + 1)) + pMin;
        }

        return peso;        
    }

    public static int[] gerarLucro(int numeroItens, int pMax, int pMin){

        int[] lucro = new int[numeroItens];

        for (int cont = 0; cont < numeroItens; cont ++) {
            lucro[cont] = (int) (Math.random() * (pMax - pMin + 1)) + pMin;
        }

        return lucro;        
    }
    
    public static int[] solucaoInicial(int numeroItens, int[] p, int[] l, int capacidadeMochila){
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

            System.out.println("Lucro -> " + valorLucro);
        return solucao;
    }

    public static void avaliaSolucao(int[] solucao, int[] p, int[] l, int numeroItens){
        int valorPeso  = 0;
        int valorLucro = 0;

        for(int cont = 0; cont < numeroItens; cont ++){

            valorPeso  += solucao[cont] * p[cont];
            valorLucro += solucao[cont] * l[cont];
        }

        System.out.println("Lucro: " + valorLucro + " Peso: " + valorPeso);
    }
    
}
