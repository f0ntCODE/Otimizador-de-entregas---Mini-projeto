package com.project.container.facade;

import com.project.container.utils.Gerador;

public class Mochila {

    /*  
     * VARIÁVEIS
     */

    private int capacidadeMochila, pesoMax, pesoMin, numeroItens = 0;
    private Gerador gerador;

     public Mochila(int capacidadeMochila, int pesoMax, int pesoMin, int numeroItens){
        this.capacidadeMochila = capacidadeMochila;
        this.pesoMax = pesoMax;
        this.pesoMin = pesoMin;
        this.numeroItens = numeroItens;
     }

     public void executarMetodoBasico(){
        new Gerador(capacidadeMochila, numeroItens);

        int[] solucao = gerador.gerarSolucaoInicial();



     }
    
}
