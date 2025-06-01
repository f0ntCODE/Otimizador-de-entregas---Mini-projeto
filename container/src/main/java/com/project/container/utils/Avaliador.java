package com.project.container.utils;

//classe avalia

import com.project.container.model.ObterResultado_Model;

public class Avaliador {

    //instâncias
    private ObterResultado_Model resultados;
    private Verificador verificador;

    public Avaliador(ObterResultado_Model resultados){

        this.resultados = resultados;
        this.verificador = new Verificador(resultados);
    }

    public int[] avaliar(){
        //variáveis
        int[] solucaoInicial = resultados.getSolucaoInicial();
        int[] vetorPeso = resultados.getPesos();
        int[] vetorLucro = resultados.getValores();

        int tamanhoVetor = resultados.getTamanhoVetor();


        int valorPesoAtual  = 0;
        int valorLucroAtual = 0;

        for(int cont = 0; cont < tamanhoVetor; cont ++){

            System.out.println("ITERAÇÃO " + cont);

            valorPesoAtual  += solucaoInicial[cont] * vetorPeso[cont];
            valorLucroAtual += solucaoInicial[cont] * vetorLucro[cont];
        }

        System.out.println("Peso máximo: " + valorPesoAtual + "\n Lucro máximo: " + valorLucroAtual);

        resultados.setSomaLucro(valorLucroAtual);
        System.out.println("DA CLASSE AVALIADOR:" + resultados.getSomaLucro());

        resultados.setSomaPeso(valorPesoAtual);

        int[] conjunto = new int[]{valorLucroAtual, valorPesoAtual};

        resultados.setAvaliado(conjunto); //armazenar

        return conjunto;
    }

    public double avaliarGanhos(){
        int[] valorAtual = resultados.getSolucaoInicial();
        int[] sucessor = resultados.getSucessores();
        int[] avalia = resultados.getAvaliado();

        int lucro = avalia[0];
        int tamanhoVetor = resultados.getTamanhoVetor();

        double ganhoTotal = 0.0;
        int valorInicial = 0;


        for(int i = 0; i < tamanhoVetor; i ++){
            int valorFinal = lucro;

            if(i < tamanhoVetor){
                valorInicial = valorAtual[i];

            }
            else{
                valorInicial = 0;
            }

            if(valorInicial > 0){
                double ganho = (Math.abs(valorFinal - valorInicial) / (double)valorInicial) * 100.0;//calcular o ganho
                ganhoTotal += ganho;
            }

        }
        double ganhoMedio = ganhoTotal / valorAtual.length;

        return (int)ganhoMedio;
    }

}
