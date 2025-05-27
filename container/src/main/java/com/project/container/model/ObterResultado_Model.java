package com.project.container.model;

//modelo para adquirir os dados

public class ObterResultado_Model {
    //dos geradores de peso e valor
    private int[]   pesos;
    private int[] valores;
    private int[] solucaoInicial;
    private int[] avaliado;

    private int tamanhoVetor;

    public ObterResultado_Model() {}//construtor

    public int[] getValores() {
        return valores;
    }

    public void setValores(int[] valores) {
        this.valores = valores;
    }

    public int[] getPesos() {
        return pesos;
    }

    public void setPesos(int[] pesos) {
        this.pesos = pesos;
    }

    public int[] getSolucaoInicial() {
        return solucaoInicial;
    }

    public void setSolucaoInicial(int[] solucaoInicial) {
        this.solucaoInicial = solucaoInicial;
    }

    public int getTamanhoVetor() {
        return tamanhoVetor;
    }

    public void setTamanhoVetor(int tamanhoVetor) {
        this.tamanhoVetor = tamanhoVetor;
    }

    public int[] getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(int[] avaliado) {
        this.avaliado = avaliado;
    }
}
