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
            peso[cont] = (int) (Math.random() * (500 - 25 + 1)) + 25;

        }

        resultado.setPesos(peso); //salvar no model

        verificador.verificarPeso();

        return peso;
    }

    public int[] gerarLucro(){

        int[] lucro = new int[numeroItens];

        for (int cont = 0; cont < numeroItens; cont ++) {
            lucro[cont] = (int) (Math.random() * (500 - 25 + 1)) + 25;
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
        resultado.setMelhorSucessorSubida(melhorVetor);
        return melhorVetor;

    }

    public int[] gerarUmSucessor(int[] solucaoAtual, int pesoMax) {
        int[] candidato = Arrays.copyOf(solucaoAtual, solucaoAtual.length);
        int[] pesos = resultado.getPesos();

        int posicao = (int)(Math.random() * solucaoAtual.length);

        // Calcula peso atual da solução
        int pesoAtual = calcularPeso(pesos, solucaoAtual);

        // Inverte o bit na posição escolhida
        candidato[posicao] = 1 - candidato[posicao];

        // Atualiza peso conforme inclusão ou remoção
        if (candidato[posicao] == 1) {
            pesoAtual += pesos[posicao];
            if (pesoAtual <= pesoMax) {
                return candidato;
            } else {
                return solucaoAtual; // rejeita candidato que ultrapassa peso
            }
        } else {
            pesoAtual -= pesos[posicao];
            return candidato; // remoção sempre válida
        }
    }

    private int calcularPeso(int[] pesos, int[] solucao) {
        int peso = 0;
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                peso += pesos[i];
            }
        }
        return peso;
    }

}