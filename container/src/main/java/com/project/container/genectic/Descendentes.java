package com.project.container.genectic;

import java.util.Random;

public class Descendentes {

    private static Random random = new Random();

    /**
     * Gera descendentes a partir da população atual.
     *
     * @param populacao população atual
     * @param fitness vetor de fitness da população
     * @param tamanhoPopulacao tamanho da população
     * @param tamanhoCromossomo tamanho do cromossomo
     * @param tc taxa de cruzamento (ex: 0.7)
     * @param tm taxa de mutação (ex: 0.02)
     * @param pesos vetor de pesos dos itens (para mutação)
     * @param capacidade capacidade máxima da mochila (para mutação)
     * @return nova população gerada
     */
    public static int[][] gerarDescendentes(int[][] populacao, int[] fitness, int tamanhoPopulacao, int tamanhoCromossomo,
                                            double tc, double tm, int[] pesos, int capacidade) {
        int quantidade = tamanhoPopulacao; // geralmente gera o mesmo tamanho da população
        if (quantidade % 2 != 0) {
            quantidade -= 1; // garante paridade para evitar estouro
        }

        int[][] descendentes = new int[quantidade][tamanhoCromossomo];

        int i = 0;

        while (i < quantidade) {
            // Seleciona pais
            int pai1 = Selecao.torneio(fitness, tamanhoPopulacao);
            int pai2 = Selecao.torneio(fitness, tamanhoPopulacao);

            // Ponto de corte aleatório para cruzamento
            int corte = 1 + random.nextInt(tamanhoCromossomo - 1);

            double valorAleatorioCruzamento = random.nextDouble();
            // Decide se vai cruzar ou copiar os pais diretamente
            if (valorAleatorioCruzamento < tc) {
                int[][] filhos = Cruzamento.cruzamento(populacao[pai1], populacao[pai2], corte, tamanhoCromossomo);
                descendentes[i] = filhos[0];
                descendentes[i + 1] = filhos[1];
            } else {
                // Copia pais diretamente
                descendentes[i] = populacao[pai1].clone();
                descendentes[i + 1] = populacao[pai2].clone();
            }

            // Aplica mutação em cada filho com probabilidade tm
            double valorAleatorioMutacao1 = random.nextDouble();
            if (valorAleatorioMutacao1 < tm) {
                descendentes[i] = Mutacao.trocaSimples(descendentes[i], tamanhoCromossomo, pesos, capacidade, tm);
            }
            double valorAleatorioMutacao2 = random.nextDouble();
            if (random.nextDouble() < tm) {
                descendentes[i + 1] = Mutacao.trocaSimples(descendentes[i + 1], tamanhoCromossomo, pesos, capacidade, tm);
            }

            i += 2;
        }

        return descendentes;
    }
}

