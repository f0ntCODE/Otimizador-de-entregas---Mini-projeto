package com.project.container.functions.tempera;

import com.project.container.model.ObterResultado_Model;
import com.project.container.utils.Avaliador;
import com.project.container.utils.Gerador;
import com.project.container.utils.Verificador;

import java.util.Arrays;

public class TemperaSimulada {

    private ObterResultado_Model resultados;
    private Verificador verificador;
    private Gerador gerador;
    private Avaliador avaliador;

    public TemperaSimulada(ObterResultado_Model resultados) {
        this.resultados  = resultados;
        this.verificador = new Verificador(resultados);
        this.avaliador   = new Avaliador(resultados);
        this.gerador     = new Gerador(resultados.getPesoMaximo(), resultados.getTamanhoVetor(), resultados);
    }

    public int[] iniciarTempera(){
        System.out.println("\n\t DADOS DA TÊMPERA SIMULADA");

        int[] atual     = Arrays.copyOf(resultados.getSolucaoInicial(), resultados.getTamanhoVetor());
        int[] lucros = Arrays.copyOf(resultados.getValores(), resultados.getTamanhoVetor());

        int valorAtual  = resultados.getSomaLucro();

        double temp        = resultados.getTemperaturaInicial();
        double tempFinal = resultados.getTemperaturaFinal();
        double fatorRedutor = resultados.getFatorRedutor();

        while(temp > tempFinal) {
            int[] sucessorNovo = gerador.gerarUmSucessor(atual, resultados.getPesoMaximo());
            int valorNovo = calcularValor(lucros, sucessorNovo);

            System.out.println("Valor " + valorNovo);

            double delta = (double)valorNovo - (double)valorAtual;

            System.out.println("delta " + delta);
            if (delta > 0) {
                System.out.println("ACHOU MELHOR");
                atual = Arrays.copyOf(sucessorNovo, resultados.getTamanhoVetor());
                valorAtual = valorNovo;

                resultados.setSomaValorTempera(valorAtual);
                resultados.setTemperaSimulada(atual);
                resultados.setSomaPesoTemperaSimulada(calcularPeso(resultados.getPesos(), atual));

            } else {
                System.out.println("ENTROU NA TÊMPERA");
                double aux = Math.exp(delta / temp);
                double aleatorio = Math.random();

                if (aleatorio < aux) {
                    System.out.println("ALEATÓRIO E AUX");
                    atual = Arrays.copyOf(sucessorNovo, resultados.getTamanhoVetor());
                    valorAtual = valorNovo;

                    resultados.setSomaValorTempera(valorAtual);
                    resultados.setTemperaSimulada(atual);
                    resultados.setSomaPesoTemperaSimulada(calcularPeso(resultados.getPesos(), atual));
                }
            }
            temp *= fatorRedutor;

            resultados.setTemperaSimulada(atual);

            verificador.verificarTemperaSimulada();
            System.out.println("Temperatura atual = " + temp);
            System.out.println("Valor atual = " + valorAtual);
            System.out.println("Valor peso = "+ calcularPeso(resultados.getPesos(), atual));

        }



        return atual;//vai retornar a melhor combinação
    }

    private static int calcularPeso(int[] pesos, int[] solucao){
        int soma = 0;

        for(int i = 0; i < pesos.length; i ++){
            if(solucao[i] == 1) {

                soma += pesos[i];
            }
        }

        return soma;
    }

    private static int calcularValor(int[] valores, int[] solucao){
        int soma = 0;

        for(int i = 0; i < valores.length; i ++){
            if(solucao[i] == 1) {

                soma += valores[i];
            }
        }

        return soma;
    }

}
