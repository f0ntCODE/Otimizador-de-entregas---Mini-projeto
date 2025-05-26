package com.project.container.functions;

import java.util.Arrays;

public class Mochila {

    private Geradores gerador;
    private Avalia avalia;
    private SolucaoInicial solucaoInicial;

    private int capacidadeMochila;
    private int pMax;
    private int pMin;
    private int numeroIntens;

    public Mochila(int capacidadeMochila, int pMax, int pMin, int numeroIntens) {
        this.capacidadeMochila = capacidadeMochila;
        this.pMax = pMax;
        this.pMin = pMin;
        this.numeroIntens = numeroIntens;

        new Geradores(capacidadeMochila, numeroIntens);
    }

    public int[] obterPeso(){

        int[] pesos = gerador.gerarPeso();

        return pesos;
    }

    public int[] obterLucros(){
        this.gerador = new Geradores(capacidadeMochila, numeroIntens);

        int[] lucros = gerador.gerarPeso();

        return lucros;
    }
}
