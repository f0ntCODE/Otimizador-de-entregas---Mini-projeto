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

            valorPesoAtual  += solucaoInicial[cont] * vetorPeso[cont];
            valorLucroAtual += solucaoInicial[cont] * vetorLucro[cont];
        }

        System.out.println("Peso máximo: " + valorPesoAtual + "\n Lucro máximo: " + valorLucroAtual);

        resultados.setSomaLucro(valorLucroAtual);
        System.out.println("DA CLASSE AVALIADOR:" + resultados.getSomaLucro());

        resultados.setSomaPeso(valorPesoAtual);

        int[] conjunto = new int[]{valorLucroAtual, valorPesoAtual};

        resultados.setAvaliado(conjunto); //armazenar


        verificador.verificarResultadosAvaliados();

        return conjunto;
    }

}
