package com.project.container.functions;

import com.project.container.utils.Calculadora;

import java.util.Arrays;

public class Tempera {

    private Sucessores sucessor;
    private Geradores gerador;
    private Avalia avalia;
    private Calculadora calc;

    public Tempera(Sucessores sucessor, Geradores gerador, Avalia avalia, Calculadora calc) {
        this.sucessor = sucessor;
        this.gerador = gerador;
        this.avalia = avalia;
        this.calc = calc;

    }

    public String[] realizarTempera(int[] solucaoInicial, int[] pesos , int[] valores, int valorInicial,
                                    double temperaturaInicial, double temperaturaFinal, double fatorRedutor, int pesoMax,
                                    int pesoAtual){

        int[] atual     = Arrays.copyOf(solucaoInicial, solucaoInicial.length);
        int valorAtual  = valorInicial;
        double temp        = temperaturaInicial;

        while(temp > temperaturaFinal){
            int[] sucessorNovo = sucessor.gerarUmSucessor(atual, pesos, pesoMax, pesoAtual);
            int valorNovo = calc.calcularValor(valores, sucessorNovo);

            double delta = valorAtual - valorNovo;

            if(delta < 0){
                atual = sucessorNovo;
                valorAtual = valorNovo;

            }
            else {
                double aux =    Math.exp(-delta/temp);
                double aleatorio = Math.random();

                if(aleatorio < aux){
                    atual = sucessorNovo;
                    valorAtual = valorNovo;
                }
            }
                temp *= fatorRedutor;

        }

        return new String[] {Arrays.toString(atual), Integer.toString(valorAtual)};//retornar o valor atual e o array
        // atual
    }
}
