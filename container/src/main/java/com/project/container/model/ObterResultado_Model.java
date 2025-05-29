package com.project.container.model;

import org.springframework.stereotype.Component;

//modelo para adquirir os dados
@Component
public class ObterResultado_Model {
    //dos geradores de peso e valor
    private int[]   pesos;
    private int[] valores;
    private int[] solucaoInicial;
    private int[] avaliado;

    private int tamanhoVetor;
    private int somaPeso;
    private int somaLucro;

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

    public int getSomaPeso() {
        return somaPeso;
    }

    public void setSomaPeso(int somaPeso) {
        this.somaPeso = somaPeso;
    }

    public int getSomaLucro() {
        return somaLucro;
    }

    public void setSomaLucro(int somaLucro) {
        this.somaLucro = somaLucro;
    }
}
