package com.project.container.genectic;

public class AlgoritmoGenetico {

    public static void executarAG(int tamanhoPopulacao, int tamanhoCromossomo, int numGeracoes,
                                  double taxaCruzamento, double taxaMutacao, int[] pesos, int[] lucros,
                                  int capacidadeMochila) {

        // Gera população inicial
        Populacao populacao = new Populacao(tamanhoPopulacao, tamanhoCromossomo);
        populacao.gerarPopulacaoInicial();

        int[][] individuos = populacao.getIndividuos();
        int melhorFitnessGlobal = Integer.MIN_VALUE;
        int[] melhorIndividuoGlobal = null;

        for (int geracao = 0; geracao < numGeracoes; geracao++) {
            int[] fitness = Fitness.avaliarPopulacao(individuos, pesos, lucros, capacidadeMochila);

            // Encontra o melhor indivíduo da geração atual
            int melhorFitness = Integer.MIN_VALUE;
            int indiceMelhor = -1;
            for (int i = 0; i < fitness.length; i++) {
                if (fitness[i] > melhorFitness) {
                    melhorFitness = fitness[i];
                    indiceMelhor = i;
                }
            }

            // Atualiza o melhor global se necessário
            if (melhorFitness > melhorFitnessGlobal) {
                melhorFitnessGlobal = melhorFitness;
                melhorIndividuoGlobal = individuos[indiceMelhor].clone();
            }

            // Calcula o peso do melhor indivíduo da geração atual
            int pesoAtual = 0;
            int[] melhorIndividuo = individuos[indiceMelhor];
            for (int j = 0; j < melhorIndividuo.length; j++) {
                if (melhorIndividuo[j] == 1) {
                    pesoAtual += pesos[j];
                }
            }

            System.out.println("Geração " + geracao + " - Melhor fitness: " + melhorFitness
                    + " | Peso atual: " + pesoAtual
                    + " | Taxa de mutação: " + taxaMutacao
                    + " | Taxa de cruzamento: " + taxaCruzamento);

            // Gera descendentes para próxima geração
            individuos = Descendentes.gerarDescendentes(individuos, fitness, tamanhoPopulacao, tamanhoCromossomo,
                    taxaCruzamento, taxaMutacao, pesos, capacidadeMochila);
        }

// Após o loop, exibe o melhor indivíduo global encontrado
        int pesoMelhorGlobal = 0;
        for (int j = 0; j < melhorIndividuoGlobal.length; j++) {
            if (melhorIndividuoGlobal[j] == 1) {
                pesoMelhorGlobal += pesos[j];
            }
        }

        System.out.println("\nMelhor solução encontrada durante toda a execução:");
        System.out.print("Indivíduo: ");
        for (int gene : melhorIndividuoGlobal) {
            System.out.print(gene);
        }
        System.out.println("\nFitness: " + melhorFitnessGlobal);
        System.out.println("Peso: " + pesoMelhorGlobal);

    }
}

