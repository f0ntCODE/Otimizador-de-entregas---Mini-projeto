package com.project.container.genectic;

import com.project.container.genectic.crossover.Crossover;
import com.project.container.genectic.descendent.Descendent;
import com.project.container.genectic.mutation.Mutation;
import com.project.container.genectic.selectionMethod.Roulette;
import com.project.container.genectic.selectionMethod.Tournament;
import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;

import java.util.Random;

import static com.project.container.genectic.descendent.Descendent.converterParaMatriz;

public class GenecticAlgorithm {

    private final int geracoesMax;
    private final double taxaCruzamento;
    private final double taxaMutacao;
    private final Random rand = new Random();

    // Dependências injetadas ou instanciadas conforme seu projeto
    private Crossover cruzamento;
    private Mutation mutacao;
    private Roulette roleta;
    private Tournament torneio;
    private Avaliador avaliador;
    private ObterResultado_Model resultado;
    private Fitness aptidao;
    private Descendent descendente;

    public GenecticAlgorithm(int geracoesMax, double taxaCruzamento, double taxaMutacao,
                             Crossover cruzamento, Mutation mutacao, Tournament torneio, Roulette roleta,
                             Avaliador avaliador, ObterResultado_Model resultado, Descendent descendente,
                             int metodoSelecao){
        this.geracoesMax = geracoesMax;
        this.taxaCruzamento = taxaCruzamento;
        this.taxaMutacao = taxaMutacao;
        this.cruzamento = cruzamento;
        this.mutacao = mutacao;
        this.roleta = roleta;
        this.torneio = torneio;
        this.avaliador = avaliador;
        this.resultado = resultado;
        this.descendente = descendente;
    }
    public void executar(ObterResultado_Model resultado, int metodoSelecao) {
        // Inicialização: população já deve estar definida no model

        for (int geracao = 0; geracao < geracoesMax; geracao++) {

            // 1. Avaliação dos indivíduos
            avaliarIndividuos(resultado);

            // 2. Cálculo da aptidão (fitness)
            aptidao.calcularFitness(resultado);

            // 3. Geração de nova população (descendentes)
            descendente.gerarDescendentes(taxaCruzamento, taxaMutacao, metodoSelecao);

            // (Opcional) Salvar melhor solução desta geração, estatísticas, etc.
        }

        // Ao final, a população do model é a última geração
        // Você pode avaliar novamente para pegar o melhor indivíduo
        avaliar(resultado);
    }

    public static void avaliarIndividuos(ObterResultado_Model model) {
        int tamanhoPopulacao = model.getTamanhoPopulacao();
        int tamanhoProblema = model.getTamanhoProblema();
        int[][] populacao = converterParaMatriz(model.getPopulacao(), tamanhoPopulacao, tamanhoProblema);
        int[] pesos = model.getPesos();
        int[] valores = model.getValores();
        int capacidadeMaxima = model.getPesoMaximo();

        int[] avaliacao = new int[tamanhoPopulacao];

        for (int i = 0; i < tamanhoPopulacao; i++) {
            int pesoTotal = 0;
            int valorTotal = 0;
            for (int j = 0; j < tamanhoProblema; j++) {
                if (populacao[i][j] == 1) {
                    pesoTotal += pesos[j];
                    valorTotal += valores[j];
                }
            }
            // Se ultrapassar a capacidade, valor é 0
            if (pesoTotal > capacidadeMaxima) {
                avaliacao[i] = 0;
            } else {
                avaliacao[i] = valorTotal;
            }
        }

        // Armazena o vetor de avaliações no model
        model.setPopAvaliado(avaliacao);
    }

    /**
     * Avalia todos os indivíduos da população e armazena os resultados no model.
     * Cada indivíduo recebe valor 0 se ultrapassar a capacidade máxima.
     */
    public static void avaliar(ObterResultado_Model model) {
        int tamanhoPopulacao = model.getTamanhoPopulacao();
        int tamanhoProblema = model.getTamanhoProblema();
        int[][] populacao = converterParaMatriz(model.getPopulacao(), tamanhoPopulacao, tamanhoProblema);
        int[] pesos = model.getPesos();
        int[] valores = model.getValores();
        int capacidadeMaxima = model.getPesoMaximo();

        int[] avaliacao = new int[tamanhoPopulacao];

        for (int i = 0; i < tamanhoPopulacao; i++) {
            int pesoTotal = 0;
            int valorTotal = 0;
            for (int j = 0; j < tamanhoProblema; j++) {
                if (populacao[i][j] == 1) {
                    pesoTotal += pesos[j];
                    valorTotal += valores[j];
                }
            }
            // Se ultrapassar a capacidade, valor é 0
            if (pesoTotal > capacidadeMaxima) {
                avaliacao[i] = 0;
            } else {
                avaliacao[i] = valorTotal;
            }
        }

        // Armazena o vetor de avaliações no model
        model.setPopAvaliado(avaliacao);
    }


}
