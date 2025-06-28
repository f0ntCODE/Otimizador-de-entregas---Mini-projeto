package com.project.container.genectic.descendent;

import com.project.container.genectic.crossover.Crossover;
import com.project.container.genectic.mutation.Mutation;
import com.project.container.genectic.selectionMethod.Roulette;
import com.project.container.genectic.selectionMethod.Tournament;
import com.project.container.model.ObterResultado_Model;

import java.util.Random;

public class Descendent {

    private ObterResultado_Model resultados;
    private Crossover cruzamento;
    private Tournament torneio;
    private Mutation mutacao;
    private Roulette roleta;
    private final Random rand = new Random();

    public Descendent(ObterResultado_Model resultados, Tournament torneio){
        this.resultados = resultados;
        this.torneio = torneio;

    }

    public void setCruzamento(Crossover cruzamento) { this.cruzamento = cruzamento; }
    public void setMutacao(Mutation mutacao) { this.mutacao = mutacao; }
    public void setTorneio(Tournament torneio) { this.torneio = torneio; }
    public void setRoleta(Roulette roleta) { this.roleta = roleta; }


    // Função principal para gerar descendentes
    public void gerarDescendentes(double taxaCruzamento, double taxaMutacao, int metodoCruzamento) {
        // Validações iniciais

        Tournament torneio = new Tournament(resultados);
        Descendent descendente = new Descendent(resultados, torneio);
        Crossover cruzamento = new Crossover(resultados);
        Mutation mutacao = new Mutation(resultados);

        if (cruzamento == null) throw new IllegalStateException("Cruzamento não inicializado!");
        if (mutacao == null) throw new IllegalStateException("Mutação não inicializada!");

        int tamanhoPopulacao = resultados.getTamanhoPopulacao();
        int tamanhoProblema = resultados.getTamanhoProblema();
        int[] populacao = resultados.getPopulacao();
        if (populacao == null || populacao.length == 0)
            throw new IllegalStateException("População não inicializada!");

        int[][] novaPopulacao = new int[tamanhoPopulacao][tamanhoProblema];
        int[][] populacaoAtual = converterParaMatriz(populacao, tamanhoPopulacao, tamanhoProblema);
        int i = 0;

        while (i < tamanhoPopulacao) {
            int indicePai1, indicePai2;
            if (metodoCruzamento == 1) {
                indicePai1 = torneio.selecionarCandidato();
                indicePai2 = torneio.selecionarCandidato();
            } else {
                indicePai1 = roleta.selecionarCandidato();
                indicePai2 = roleta.selecionarCandidato();
            }

            resultados.setPop1(populacaoAtual[indicePai1]);
            resultados.setPop2(populacaoAtual[indicePai2]);
            resultados.setCorte(rand.nextInt(Math.max(1, tamanhoProblema)));

            if (rand.nextDouble() < taxaCruzamento) {
                cruzamento.cruzarIndividuos();
                novaPopulacao[i] = resultados.getDescendendeCruzado1();
                if (i + 1 < tamanhoPopulacao)
                    novaPopulacao[i + 1] = resultados.getDescendendeCruzado2();
            } else {
                novaPopulacao[i] = populacaoAtual[indicePai1].clone();
                if (i + 1 < tamanhoPopulacao)
                    novaPopulacao[i + 1] = populacaoAtual[indicePai2].clone();
            }

            if (rand.nextDouble() < taxaMutacao) {
                mutacao.mutacao(novaPopulacao[i], resultados);
            }
            if (i + 1 < tamanhoPopulacao && rand.nextDouble() < taxaMutacao) {
                mutacao.mutacao(novaPopulacao[i + 1], resultados);
            }

            i += 2;
        }
        resultados.setPopulacao(converterParaVetor(novaPopulacao));
    }

    public static int[][] converterParaMatriz(int[] vetorPopulacao, int tamanhoPopulacao, int tamanhoIndividuo) {
        int[][] matriz = new int[tamanhoPopulacao][tamanhoIndividuo];
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = 0; j < tamanhoIndividuo; j++) {
                matriz[i][j] = vetorPopulacao[i * tamanhoIndividuo + j];
            }
        }
        return matriz;
    }

    public static int[] converterParaVetor(int[][] matrizPopulacao) {
        int tamanhoPopulacao = matrizPopulacao.length;
        int tamanhoIndividuo = matrizPopulacao[0].length;
        int[] vetor = new int[tamanhoPopulacao * tamanhoIndividuo];
        for (int i = 0; i < tamanhoPopulacao; i++) {
            for (int j = 0; j < tamanhoIndividuo; j++) {
                vetor[i * tamanhoIndividuo + j] = matrizPopulacao[i][j];
            }
        }
        return vetor;
    }

}
