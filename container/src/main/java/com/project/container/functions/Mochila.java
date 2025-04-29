package com.project.container.functions;

import java.util.Arrays;

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
            System.out.println("Solução inicial: " + Arrays.toString(solucao));

        return solucao;
    }

    public static String avaliaSolucao(int[] solucao, int[] p, int[] l, int numeroItens){
        int valorPeso  = 0;
        int valorLucro = 0;

        for(int cont = 0; cont < numeroItens; cont ++){

            valorPeso  += solucao[cont] * p[cont];
            valorLucro += solucao[cont] * l[cont];
        }

        System.out.println("Peso máximo: " + valorPeso + "\n Lucro máximo: " + valorLucro);

        return "Solução inicial: " + Arrays.toString(solucao) + " \n Lucro máximo: " + valorLucro + " \n Peso máximo: " + valorPeso;
    }

    public static String[] obterResultados(int capacidadeMochila, int pMax, int pMin, int numeroItens) {
        // Gerar pesos e lucros
        int[] pesos = gerarPeso(numeroItens, pMax, pMin);
        int[] lucros = gerarLucro(numeroItens, pMax, pMin);
    
        // Obter solução inicial
        int[] solucao = solucaoInicial(numeroItens, pesos, lucros, capacidadeMochila);
    
        // Avaliar a solução
        String resultadoAvaliado = avaliaSolucao(solucao, pesos, lucros, numeroItens);
            
        // Formatar os resultados
        String resultadoPesos = "Todos os Pesos (KG): "   + Arrays.toString(pesos);
        String resultadoLucros = "Todos os Lucros (R$): " + Arrays.toString(lucros);
    
        return new String[]{resultadoPesos, resultadoLucros, resultadoAvaliado};
    }
}
