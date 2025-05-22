package com.project.container.functions;

public class Geradores {

    private int capacidadeMochila;
    private int pesoMax;
    private int pesoMin;
    private int numeroItens;
    

    public Geradores(int capacidadeMochila, int pesoMax, int pesoMin, int numeroItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.pesoMax = pesoMax;
        this.pesoMin = pesoMin;
        this.numeroItens = numeroItens;
    }

    public int[] gerarPeso(int numeroItens, int pesoMax, int pesoMin){

        int[] peso = new int[numeroItens];
        
        for (int cont = 0; cont < numeroItens; cont ++) {
            peso[cont] = (int) (Math.random() * (pesoMax - pesoMin + 1)) + pesoMin;

        }

        return peso;        
    }

    public int[] gerarLucro(int numeroItens, int pesoMax, int pesoMin){

        int[] lucro = new int[numeroItens];

        for (int cont = 0; cont < numeroItens; cont ++) {
            lucro[cont] = (int) (Math.random() * (pesoMax - pesoMin + 1)) + pesoMin;
        }
        
        return lucro;        
    }

}
