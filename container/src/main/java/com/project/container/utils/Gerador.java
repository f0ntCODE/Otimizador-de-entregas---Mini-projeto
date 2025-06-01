package com.project.container.utils;

//classe que vai gerar os valores necessários

import com.project.container.model.ObterResultado_Model;

import java.util.Arrays;

public class Gerador {

    private int capacidadeMochila;
    private int numeroItens;
    private ObterResultado_Model resultado;
    private Verificador verificador;


    public Gerador(int capacidadeMochila, int numeroItens, ObterResultado_Model resultado) {
        this.capacidadeMochila = capacidadeMochila;
        this.numeroItens = numeroItens;

        this.resultado = resultado;
        this.verificador = new Verificador(resultado);

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

    public int[] gerarSucessores(){
        //recursos
        int tamanhoVetor = resultado.getTamanhoVetor();
        int melhorValor = resultado.getSomaLucro();
        int pesoMax = resultado.getPesoMaximo();

        //arrays
        int[] pesos = resultado.getPesos();
        int[] lucros = resultado.getValores();
        int[] solucaoAtual = resultado.getSolucaoInicial();
        int[] melhorVetor = Arrays.copyOf(solucaoAtual, tamanhoVetor);

    try {
        for (int i = 0; i < tamanhoVetor; i++) {
            if (solucaoAtual[i] == 1) {
                int[] candidato = Arrays.copyOf(solucaoAtual, tamanhoVetor);
                candidato[i] = 0; // remove item i

                // Calcule peso e valor do candidato após remoção
                int pesoCandidato = 0;
                int valorCandidato = 0;
                for (int k = 0; k < tamanhoVetor; k++) {
                    if (candidato[k] == 1) {
                        pesoCandidato += pesos[k];
                        valorCandidato += lucros[k];
                    }
                }

                for (int j = 0; j < tamanhoVetor; j++) {
                    if (candidato[j] == 0 && (pesoCandidato + pesos[j]) <= pesoMax) {
                        candidato[j] = 1; // tenta adicionar item j
                        int valorNovo = 0;
                        int pesoNovo = 0;
                        for (int k = 0; k < tamanhoVetor; k++) {
                            if (candidato[k] == 1) {
                                valorNovo += lucros[k];
                                pesoNovo += pesos[k];
                            }
                        }
                        if (valorNovo > melhorValor && pesoNovo <= pesoMax) {
                            melhorValor = valorNovo;
                            melhorVetor = Arrays.copyOf(candidato, tamanhoVetor);
                        }
                        candidato[j] = 0; // desfaz adição
                    }
                }
            }
        }
    }catch(ArrayIndexOutOfBoundsException ex){
        System.err.println("ERRO: Problema na classe Sucessores: " + ex);
    }
        resultado.setSucessores(melhorVetor);
        return melhorVetor;

    }

    public int[] gerarUmSucessor(){

        int[] candidato = Arrays.copyOf(resultado.getSolucaoInicial(), resultado.getTamanhoVetor());
        int[] pesos = resultado.getPesos();
        int[] solucaoInicial = resultado.getSolucaoInicial();

        int posicao = (int)(Math.random() * (resultado.getTamanhoVetor() - 1)); //gerar números aleatórios
        int pAtual = resultado.getSomaPeso();
        int pesoMax = resultado.getPesoMaximo();

        candidato[posicao] = 1 - candidato[posicao];

        if(candidato[posicao] == 1) {   //entre aqui se o ítem estiver colocado na mochila
            pAtual += pesos[posicao];  //somar o peso
            System.out.println("peso atual: " + pAtual);

            boolean ultrapassou = (pAtual > pesoMax) ? true : false;

            if (!ultrapassou) {//o peso não foi ultrapassado
                System.out.println("PESO NÃO ULTRAPASSADO");
                return candidato;       //retorne o candidato e interrompa o fluxo
            }
            else{
                System.out.println("PESO ULTRAPASSADO");
                return solucaoInicial;
            }
        }

        return candidato;
    }
}