package com.project.container.model;

import org.springframework.stereotype.Component;

//modelo para adquirir os dados

public class ObterResultado_Model {
    //dos geradores de peso e valor
    private int[]   pesos;
    private int[] valores;
    private int[] solucaoInicial;
    private int[] avaliado;
    private int[] sucessores;
    private int[] melhorSucessorSubida;
    private int[] SubidaEncosta;
    private int[] subidaEncostaTentativa;
    private int[] temperaSimulada;
    private int[] melhorSucessorUnico;

    private int tamanhoVetor;
    private int somaPeso;
    private int somaLucro;
    private int pesoMaximo;
    private int pesoMinimo;
    private int maxTentativas;
    private int temperaturaInicial;
    private int pesoSubidaEncosta;
    private int somaValorSubidaEncosta;
    private int somaPesoSubidaEncosta;
    private int somaValorTempera;
    private int somaPesoTemperaSimulada;

    private double temperaturaFinal;
    private double fatorRedutor;
    private double ganho;

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

    public int getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public int getPesoMinimo() {
        return pesoMinimo;
    }

    public void setPesoMinimo(int pesoMinimo) {
        this.pesoMinimo = pesoMinimo;
    }

    public int[] getSucessores() {
        return sucessores;
    }

    public void setSucessores(int[] sucessores) {
        this.sucessores = sucessores;
    }

    public int getMaxTentativas() {return maxTentativas;}

    public void setMaxTentativas(int maxTentativas) {this.maxTentativas = maxTentativas;}

    public int[] getSubidaEncostaTentativa() {return subidaEncostaTentativa;}

    public void setSubidaEncostaTentativa(int[] subidaEncostaTentativa) {this.subidaEncostaTentativa = subidaEncostaTentativa;}

    public int[] getSubidaEncosta() {return SubidaEncosta;}

    public void setSubidaEncosta(int[] subidaEncosta) {SubidaEncosta = subidaEncosta;}

    public int[] getTemperaSimulada() {return temperaSimulada;}

    public void setTemperaSimulada(int[] temperaSimulada) {this.temperaSimulada = temperaSimulada;}

    public int getTemperaturaInicial() {return temperaturaInicial;}

    public void setTemperaturaInicial(int temperaturaInicial) {this.temperaturaInicial = temperaturaInicial;}

    public double getTemperaturaFinal() {return temperaturaFinal;}

    public void setTemperaturaFinal(double temperaturaFinal) {this.temperaturaFinal = temperaturaFinal;}

    public double getFatorRedutor() {return fatorRedutor;}

    public void setFatorRedutor(double fatorRedutor) {this.fatorRedutor = fatorRedutor;}

    public double getGanho() {return ganho;}

    public void setGanho(double ganho) {this.ganho = ganho;}

    public int getPesoSubidaEncosta() {return pesoSubidaEncosta;}

    public void setPesoSubidaEncosta(int pesoSubidaEncosta) {this.pesoSubidaEncosta = pesoSubidaEncosta;}

    public int[] getMelhorSucessorSubida() {return melhorSucessorSubida;}

    public void setMelhorSucessorSubida(int[] melhorSucessorSubida) {this.melhorSucessorSubida = melhorSucessorSubida;}

    public int[] getMelhorSucessorUnico() {return melhorSucessorUnico;}

    public void setMelhorSucessorUnico(int[] melhorSucessorUnico) {this.melhorSucessorUnico = melhorSucessorUnico;}

    public int getSomaValorSubidaEncosta() {
        return somaValorSubidaEncosta;
    }

    public void setSomaValorSubidaEncosta(int somaValorSubidaEncosta) {
        this.somaValorSubidaEncosta = somaValorSubidaEncosta;
    }

    public int getSomaPesoSubidaEncosta() {
        return somaPesoSubidaEncosta;
    }

    public void setSomaPesoSubidaEncosta(int somaPesoSubidaEncosta) {
        this.somaPesoSubidaEncosta = somaPesoSubidaEncosta;
    }

    public int getSomaValorTempera() {
        return somaValorTempera;
    }

    public void setSomaValorTempera(int somaValorTempera) {
        this.somaValorTempera = somaValorTempera;
    }

    public int getSomaPesoTemperaSimulada() {
        return somaPesoTemperaSimulada;
    }

    public void setSomaPesoTemperaSimulada(int somaPesoTemperaSimulada) {
        this.somaPesoTemperaSimulada = somaPesoTemperaSimulada;
    }
}
