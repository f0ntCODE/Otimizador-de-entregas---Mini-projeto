package com.project.container.utils;

import com.project.container.model.ObterResultado_Model;

public class Ganho {

    private ObterResultado_Model resultados;

    public Ganho(ObterResultado_Model resultados) {
        this.resultados = resultados;
    }

    /**
     * Calcula o vetor de ganhos percentuais entre os métodos:
     * [0] Têmpera Simulada vs Subida de Encosta
     * [1] Têmpera Simulada vs Subida de Encosta com Tentativa
     * [2] Subida de Encosta vs Subida de Encosta com Tentativa
     * @return vetor de ganhos percentuais
     */
    public double[] calcularGanhos() {
        // Obtém os valores finais de cada método
        int valorTempera = resultados.getSomaValorTempera();
        int valorEncosta = resultados.getSomaValorSubidaEncosta();
        int valorEncostaTentativa = resultados.getSomaValorSubidaEncostaTentativa(); // Supondo que exista esse getter

        // Calcula os ganhos percentuais
        double ganhoTemperaVsEncosta = calcularGanhoPercentual(valorTempera, valorEncosta);
        double ganhoTemperaVsEncostaTentativa = calcularGanhoPercentual(valorTempera, valorEncostaTentativa);
        double ganhoEncostaVsEncostaTentativa = calcularGanhoPercentual(valorEncosta, valorEncostaTentativa);

        // Retorna os resultados em um vetor
        return new double[] {
                ganhoTemperaVsEncosta,
                ganhoTemperaVsEncostaTentativa,
                ganhoEncostaVsEncostaTentativa
        };
    }

    /**
     * Calcula o ganho percentual entre dois valores.
     * @param valorFinal valor final (após otimização)
     * @param valorInicial valor inicial (comparativo)
     * @return ganho percentual
     */
    private double calcularGanhoPercentual(int valorFinal, int valorInicial) {
        if (valorInicial == 0) return 0.0;
        return ((double) (valorFinal - valorInicial) / valorInicial) * 100.0;
    }
}
