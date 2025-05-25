package com.project.container.functions;

public class Geradores {

    private int capacidadeMochila;
    private int numeroItens;
    

    public Geradores(int capacidadeMochila, int numeroItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.numeroItens = numeroItens;
    }

    public int[] gerarPeso(int numeroItens){

        int[] peso = new int[numeroItens];
        
        for (int cont = 0; cont < numeroItens; cont ++) {
            peso[cont] = (int) (Math.random() * (550 - 50 + 1)) + 50;

        }

        return peso;        
    }

    public int[] gerarLucro(int numeroItens){

        int[] lucro = new int[numeroItens];

        for (int cont = 0; cont < numeroItens; cont ++) {
            lucro[cont] = (int) (Math.random() * (550 - 50 + 1)) + 50;
        }
        
        return lucro;        
    }

}
