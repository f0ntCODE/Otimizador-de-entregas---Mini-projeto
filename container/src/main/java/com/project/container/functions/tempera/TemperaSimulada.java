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

        //validadores
        verificador.verificarSolucaoInicial();
        verificador.verificarLucros();
        verificador.verificarSomaLucros();
        verificador.verificarTemperaturaInicial();
        verificador.verificarTemperaturaFinal();
        verificador.verificarFatorRedutor();

        while(temp > tempFinal) {
            int[] sucessorNovo = gerador.gerarUmSucessor();
            int valorNovo = calcularValor(lucros, sucessorNovo);

            double delta = valorNovo - valorAtual;

            if (delta > 0) {
                atual = sucessorNovo;
                valorAtual = valorNovo;

            } else {
                double aux = Math.exp(delta / temp);
                double aleatorio = Math.random();

                if (aleatorio < aux) {
                    atual = sucessorNovo;
                    valorAtual = valorNovo;
                }
            }
            temp *= fatorRedutor;

            resultados.setTemperaSimulada(atual);

            verificador.verificarTemperaSimulada();
            System.out.println("Temperatura atual = " + temp);
        }

            System.out.println("Valor atual = " + valorAtual);
            System.out.println("Valor peso = "+ calcularPeso(resultados.getPesos(), atual));

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
