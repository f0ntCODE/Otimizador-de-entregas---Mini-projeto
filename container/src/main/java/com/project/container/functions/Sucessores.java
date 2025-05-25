package com.project.container.functions;

import java.util.Arrays;

public abstract class Sucessores {

    private Avalia avalia;

    //preciso importar os valores de Avalia e de Solução inicial
    public int[] gerarSucessores(int[] vetorAtual, int[] pesos, int[] valores, int pesoMax) {
        int tamanhoVetor = vetorAtual.length;
        int[] melhorVetor = Arrays.copyOf(vetorAtual, tamanhoVetor);
        int melhorValor = Integer.parseInt(avalia.avaliaSolucao(vetorAtual, pesos, valores, tamanhoVetor));

        for (int i = 0; i < tamanhoVetor; i++) {
            if (vetorAtual[i] == 1) {
                int[] candidato = Arrays.copyOf(vetorAtual, tamanhoVetor);
                candidato[i] = 0; // remove item i

                // Calcule peso e valor do candidato após remoção
                int pesoCandidato = 0;
                int valorCandidato = 0;
                for (int k = 0; k < tamanhoVetor; k++) {
                    if (candidato[k] == 1) {
                        pesoCandidato += pesos[k];
                        valorCandidato += valores[k];
                    }
                }

                for (int j = 0; j < tamanhoVetor; j++) {
                    if (candidato[j] == 0 && (pesoCandidato + pesos[j]) <= pesoMax) {
                        candidato[j] = 1; // tenta adicionar item j
                        int valorNovo = 0;
                        int pesoNovo = 0;
                        for (int k = 0; k < tamanhoVetor; k++) {
                            if (candidato[k] == 1) {
                                valorNovo += valores[k];
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
        return melhorVetor;
    }

    public int[] gerarUmSucessor(int[] solucaoInicial, int[] pesos, int pesoMax, int pesoAtual){
        int[] candidato = Arrays.copyOf(solucaoInicial, solucaoInicial.length);

        int posicao = (int)(Math.random() * 6); //gerar números aleatórios entre 0 e 5
        int pAtual = pesoAtual;

        candidato[posicao] = 1 - candidato[posicao];

        if(candidato[posicao] == 1) {   //entre aqui se o ítem estiver colocado na mochila
            pAtual += pesos[posicao];  //somar o peso

            boolean ultrapassou = (pAtual <= pesoMax) ? false : true;

            if (!ultrapassou) {    //o peso não foi ultrapassado
                return candidato;       //retorne o candidato e interrompa o fluxo
            }
            else{
                return solucaoInicial;
            }
        }

        return candidato;
    }


}
