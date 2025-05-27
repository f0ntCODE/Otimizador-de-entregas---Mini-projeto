package com.project.container.utils;

//classe que vai gerar os valores necessários

import com.project.container.model.ObterResultado_Model;

import java.util.Arrays;

public class Gerador {

    private int capacidadeMochila;
    private int numeroItens;
    private ObterResultado_Model resultado;
    private Verificador verificador;


    public Gerador(int capacidadeMochila, int numeroItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.numeroItens = numeroItens;

        resultado = new ObterResultado_Model();
        gerarPeso();
        gerarLucro();

    }

    public int[] gerarPeso(){

        int[] peso = new int[numeroItens];

        for (int cont = 0; cont < numeroItens; cont ++) {
            peso[cont] = (int) (Math.random() * (550 - 50 + 1)) + 50;

        }

        resultado.setPesos(peso); //salvar no model

        verificador.verificarPeso();

        return peso;
    }

    public int[] gerarLucro(){

        int[] lucro = new int[numeroItens];

        for (int cont = 0; cont < numeroItens; cont ++) {
            lucro[cont] = (int) (Math.random() * (550 - 50 + 1)) + 50;
        }

        resultado.setValores(lucro); //salvar o lucro

        verificador.verificarLucros();

        return lucro;
    }

    public int[] gerarSolucaoInicial(){
        this.capacidadeMochila = capacidadeMochila;
        this.numeroItens = numeroItens;

        int[] solucaoInicial = new int[numeroItens]; //criar solução inicial com base no tamanho do vetor

        int valorLucro = 0;
        int valorPeso  = 0;
        int maxTentativas = numeroItens * 2;
        int tentativa = 0;

        int[] vetorPeso  = resultado.getPesos();
        int[] vetorLucro = resultado.getValores();

        while(valorPeso < capacidadeMochila && tentativa < maxTentativas){
            int i = (int) (Math.random() * numeroItens);

            if (solucaoInicial[i] == 0 && (valorPeso + vetorPeso[i]) <= capacidadeMochila) {
                solucaoInicial[i] = 1;
                valorPeso  += vetorPeso[i];
                valorLucro += vetorLucro[i];
            }

            tentativa ++;
        }

        resultado.setSolucaoInicial(solucaoInicial); //salvar solucao Inicial

        verificador.verificarSolucaoInicial();

        return solucaoInicial;
    }

}