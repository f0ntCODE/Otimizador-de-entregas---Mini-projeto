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

        resultados.setSomaLucro(valorLucroAtual);
        System.out.println("DA CLASSE AVALIADOR:" + resultados.getSomaLucro());

        resultados.setSomaPeso(valorPesoAtual);

        int[] conjunto = new int[]{valorLucroAtual, valorPesoAtual};

        resultados.setAvaliado(conjunto); //armazenar

        return conjunto;
    }

    public int[] avaliarSucessor(int[] sucessor){
        //variáveis
        int[] vetorPeso = resultados.getPesos();
        int[] vetorLucro = resultados.getValores();

        int tamanhoVetor = resultados.getTamanhoVetor();


        int valorPesoAtual  = 0;
        int valorLucroAtual = 0;

        for(int cont = 0; cont < tamanhoVetor; cont ++){

            valorPesoAtual  += sucessor[cont] * vetorPeso[cont];
            valorLucroAtual += sucessor[cont] * vetorLucro[cont];
        }

        System.out.println("Peso máximo: " + valorPesoAtual + "\n Lucro máximo: " + valorLucroAtual);

        //resultados.setSomaLucro(valorLucroAtual);
        System.out.println("DA CLASSE AVALIADOR DO SUCESSOR: R$ " + valorLucroAtual + "\nPESO: KG " + valorPesoAtual);

        //resultados.setSomaPeso(valorPesoAtual);

        int[] conjunto = new int[]{valorLucroAtual, valorPesoAtual};

        //resultados.setAvaliado(conjunto); //armazenar

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

    public void avaliarIndividuos(ObterResultado_Model resultados) {
        int tamanhoPopulacao = resultados.getTamanhoPopulacao();
        int tamanhoProblema = resultados.getTamanhoProblema();
        int[] populacao = resultados.getPopulacao();
        int[] pesos = resultados.getPesos();
        int[] valores = resultados.getValores();
        int pesoMaximo = resultados.getPesoMaximo();

        if (populacao == null || populacao.length != tamanhoPopulacao * tamanhoProblema)
            throw new IllegalStateException("População não inicializada ou tamanho inconsistente! Esperado: "
                    + (tamanhoPopulacao * tamanhoProblema) + ", atual: " + (populacao == null ? 0 : populacao.length));
        if (pesos == null || pesos.length != tamanhoProblema)
            throw new IllegalStateException("Vetor de pesos não inicializado ou tamanho inconsistente!");
        if (valores == null || valores.length != tamanhoProblema)
            throw new IllegalStateException("Vetor de valores não inicializado ou tamanho inconsistente!");

        int[] lucros = new int[tamanhoPopulacao];
        int[] pesosInd = new int[tamanhoPopulacao];

        for (int i = 0; i < tamanhoPopulacao; i++) {
            int lucro = 0;
            int peso = 0;
            for (int j = 0; j < tamanhoProblema; j++) {
                int gene = populacao[i * tamanhoProblema + j];
                lucro += gene * valores[j];
                peso  += gene * pesos[j];
            }
            if (peso > pesoMaximo) {
                lucro = 0;
            }
            lucros[i] = lucro;
            pesosInd[i] = peso;
        }
        resultados.setLucrosIndividuais(lucros);
        resultados.setPesosIndividuais(pesosInd);
    }


    public static int calcularValorIndividuo(int[] individuo, int[] vetorValores) {
        int valorTotal = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i] == 1) {
                valorTotal += vetorValores[i];
            }
        }
        return valorTotal;
    }


}
